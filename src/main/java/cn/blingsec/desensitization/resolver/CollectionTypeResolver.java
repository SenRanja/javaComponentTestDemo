package cn.blingsec.desensitization.resolver;

import cn.blingsec.desensitization.support.InstanceCreators;
import cn.blingsec.desensitization.util.ReflectionUtil;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 泛型{@link Collection}对象解析器
 *
 * @author 
 */
public class CollectionTypeResolver implements TypeResolver<Collection<Object>, AnnotatedParameterizedType> {

    @Override
    public Collection<Object> resolve(Collection<Object> value, AnnotatedParameterizedType annotatedParameterizedType) {
        return value.parallelStream().map(o -> TypeResolvers.resolve(o, annotatedParameterizedType.getAnnotatedActualTypeArguments()[0]))
                .collect(Collectors.collectingAndThen(Collectors.toList(), erased -> {
                    Collection<Object> collection = InstanceCreators.getInstanceCreator(ReflectionUtil.getClass(value)).create();
                    collection.addAll(erased);
                    return collection;
                }));
    }

    @Override
    public boolean support(Object value, AnnotatedType annotatedType) {
        return value instanceof Collection && annotatedType instanceof AnnotatedParameterizedType;
    }

    @Override
    public int order() {
        return 0;
    }
}
