package cn.blingsec.desensitization.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记注解，表明当前注解是一个敏感注解。用户可以基于此注解扩展自己的敏感注解，
 * 有关如何定义自己的敏感注解可以参考{@link cn.blingsec.desensitization.annotation}包下已存在的敏感注解定义。
 *
 * @author 
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveAnnotation {
}
