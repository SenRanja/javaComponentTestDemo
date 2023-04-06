package cn.blingsec.desensitization.resolver;

import cn.blingsec.desensitization.util.ReflectionUtil;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * {@link Array}对象解析器
 *
 * @author 
 */
public class ArrayTypeResolver implements TypeResolver<Object[], AnnotatedArrayType> {

    @Override
    public Object[] resolve(Object[] value, AnnotatedArrayType annotatedArrayType) {
        return Arrays.stream(value).parallel().map(o -> TypeResolvers.resolve(o, annotatedArrayType.getAnnotatedGenericComponentType()))
                .<Object>toArray(length -> ReflectionUtil.newArray(value.getClass().getComponentType(), length));
    }

    @Override
    public boolean support(Object value, AnnotatedType annotatedType) {
        return value instanceof Object[] && annotatedType instanceof AnnotatedArrayType;
    }

    @Override
    public int order() {
        return 2;
    }

}
