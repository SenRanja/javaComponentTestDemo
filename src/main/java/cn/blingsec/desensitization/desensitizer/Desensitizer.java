package cn.blingsec.desensitization.desensitizer;

import java.lang.annotation.Annotation;

/**
 * 脱敏器
 *
 * @param <A> 敏感注解类型
 * @param <T> 需要脱敏的对象类型
 * @author 
 */
public interface Desensitizer<T, A extends Annotation> {

    /**
     * 由子类实现敏感信息脱敏逻辑
     *
     * @param target     需要脱敏的目标
     * @param annotation 目标对象上的敏感注解
     * @return 脱敏后的结果
     */
    T desensitize(T target, A annotation);

}
