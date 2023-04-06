package cn.blingsec.desensitization.resolver;

import cn.blingsec.desensitization.annotation.CascadeSensitive;
import cn.blingsec.desensitization.support.InstanceCreators;
import cn.blingsec.desensitization.util.ReflectionUtil;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Modifier;

/**
 * 级联对象解析器，只会处理被<b>直接</b>标注{@link CascadeSensitive}注解、非{@code final}、非{@code null}的对象。
 *
 * @author 
 * @see CascadeSensitive
 */
public class CascadeTypeResolver implements TypeResolver<Object, AnnotatedType> {

    @Override
    public Object resolve(Object value, AnnotatedType annotatedType) {
        Class<?> clazz = value.getClass();
        Object newObject = InstanceCreators.getInstanceCreator(clazz).create();
        ReflectionUtil.listAllFields(clazz).parallelStream().forEach(field -> {
            Object fieldValue;
            if (!Modifier.isFinal(field.getModifiers()) && (fieldValue = ReflectionUtil.getFieldValue(value, field)) != null) {
                ReflectionUtil.setFieldValue(newObject, field, TypeResolvers.resolve(fieldValue, field.getAnnotatedType()));
            }
        });
        return newObject;
    }

    @Override
    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null && annotatedType.getDeclaredAnnotation(CascadeSensitive.class) != null;
    }

    @Override
    public int order() {
        return LOWEST_PRIORITY;
    }
}
