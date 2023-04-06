package cn.blingsec.desensitization.resolver;

import cn.blingsec.desensitization.support.InstanceCreators;
import cn.blingsec.desensitization.util.ReflectionUtil;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 泛型{@link Map}对象解析器
 *
 * @author 
 */
public class MapTypeResolver implements TypeResolver<Map<Object, Object>, AnnotatedParameterizedType> {

    @Override
    public Map<Object, Object> resolve(Map<Object, Object> value, AnnotatedParameterizedType annotatedParameterizedType) {
        AnnotatedType[] annotatedActualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
        return value.entrySet().parallelStream().collect(Collectors.collectingAndThen(Collectors.toMap(
                entry -> TypeResolvers.resolve(entry.getKey(), annotatedActualTypeArguments[0]),
                entry -> TypeResolvers.resolve(entry.getValue(), annotatedActualTypeArguments[1])), erased -> {
            Map<Object, Object> map = InstanceCreators.getInstanceCreator(ReflectionUtil.getClass(value)).create();
            map.putAll(erased);
            return map;
        }));
    }

    @Override
    public boolean support(Object value, AnnotatedType annotatedType) {
        return value instanceof Map && annotatedType instanceof AnnotatedParameterizedType;
    }

    @Override
    public int order() {
        return 1;
    }
}
