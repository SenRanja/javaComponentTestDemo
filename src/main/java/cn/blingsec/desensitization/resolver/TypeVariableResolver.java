package cn.blingsec.desensitization.resolver;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * {@link TypeVariable}对象解析器
 *
 * @author 
 */
public class TypeVariableResolver implements TypeResolver<Object, AnnotatedTypeVariable> {

    @Override
    public Object resolve(Object value, AnnotatedTypeVariable annotatedTypeVariable) {
        return Arrays.stream(annotatedTypeVariable.getAnnotatedBounds()).reduce(value, TypeResolvers::resolve, (u1, u2) -> null);
    }

    @Override
    public boolean support(Object value, AnnotatedType annotatedType) {
        return value != null && annotatedType instanceof AnnotatedTypeVariable;
    }

    @Override
    public int order() {
        return HIGHEST_PRIORITY;
    }
}
