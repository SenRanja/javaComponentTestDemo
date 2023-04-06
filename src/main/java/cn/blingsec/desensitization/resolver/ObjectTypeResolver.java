package cn.blingsec.desensitization.resolver;

import cn.blingsec.desensitization.annotation.SensitiveAnnotation;
import cn.blingsec.desensitization.desensitizer.Desensitizer;
import cn.blingsec.desensitization.exception.DesensitizationException;
import cn.blingsec.desensitization.support.InstanceCreators;
import cn.blingsec.desensitization.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 解析被<b>直接</b>标注敏感注解的对象，只会处理对象上直接存在的第一个敏感注解。
 *
 * @author 
 * @see SensitiveAnnotation
 */
public class ObjectTypeResolver implements TypeResolver<Object, AnnotatedType> {

    /**
     * 脱敏器缓存
     */
    private static final ConcurrentMap<Class<Desensitizer<Object, Annotation>>, Desensitizer<Object, Annotation>> DESENSITIZER_CACHE = new ConcurrentHashMap<>();

    /**
     * 敏感注解中脱敏器方法缓存
     */
    private static final ConcurrentMap<Class<? extends Annotation>, Method> DESENSITIZER_METHOD_CACHE = new ConcurrentHashMap<>();

    @Override
    public Object resolve(Object value, AnnotatedType annotatedType) {
        return Arrays.stream(annotatedType.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(SensitiveAnnotation.class))
                .findFirst()
                .map(sensitiveAnnotation -> getDesensitizer(sensitiveAnnotation).desensitize(value, sensitiveAnnotation))
                .orElse(value);
    }

    @Override
    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null;
    }

    @Override
    public int order() {
        return LOWEST_PRIORITY - 1;
    }

    /**
     * 实例化敏感注解对应的{@link Desensitizer}。
     * 目前对同一个{@link Class}的脱敏器对象都添加了缓存。
     *
     * @param annotation 敏感注解
     * @return 敏感注解对应的 {@link Desensitizer}
     */
    private Desensitizer<Object, Annotation> getDesensitizer(Annotation annotation) {
        try {
            Method desensitizerMethod = DESENSITIZER_METHOD_CACHE.computeIfAbsent(annotation.annotationType(), annotationClass -> ReflectionUtil.getDeclaredMethod(annotationClass, "desensitizer"));
            return DESENSITIZER_CACHE.computeIfAbsent(ReflectionUtil.invokeMethod(annotation, desensitizerMethod), clazz -> InstanceCreators.getInstanceCreator(clazz).create());
        } catch (Exception e) {
            throw new DesensitizationException(String.format("实例化敏感注解%s的脱敏器失败。", annotation.annotationType()), e);
        }
    }

}
