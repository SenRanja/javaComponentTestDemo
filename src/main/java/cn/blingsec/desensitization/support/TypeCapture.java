package cn.blingsec.desensitization.support;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 捕获{@link T}运行时被注解的类型
 *
 * @param <T> 需要捕获的对象类型
 * @author 
 */
abstract class TypeCapture<T> {

    /**
     * @return {@link T}运行时被注解的类型
     */
    AnnotatedType capture() {
        Class<?> clazz = getClass();
        Type superclass = clazz.getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            throw new IllegalArgumentException(String.format("%s必须是参数化类型", superclass));
        }
        return ((AnnotatedParameterizedType) clazz.getAnnotatedSuperclass()).getAnnotatedActualTypeArguments()[0];
    }

}
