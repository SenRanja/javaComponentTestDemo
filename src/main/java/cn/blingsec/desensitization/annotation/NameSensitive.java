package cn.blingsec.desensitization.annotation;

import cn.blingsec.desensitization.desensitizer.NameDesensitizer;
import cn.blingsec.desensitization.desensitizer.Condition;
import cn.blingsec.desensitization.desensitizer.Desensitizer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 中文名称敏感标记注解，默认的脱敏规则：擦除目标对象中除了第一位字符以外的所有字符。
 * <p><strong>注意：默认的脱敏器是{@link NameDesensitizer}，该脱敏器只会处理{@link String}
 * 类型的对象，并且脱敏时不会校验目标对象的合法性，请确保目标对象是合法的中文名称，
 * 否则会抛出任何可能的 {@link RuntimeException}。</strong></p>
 *
 * @author 
 */
@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface NameSensitive {
    /**
     * @return 银行卡号的最小长度
     */
    int minLength() default 2;


    /**
     * @return 处理被 {@link NameSensitive}标记的对象脱敏器，可以自定义子类重写默认的处理逻辑。
     */
    Class<? extends Desensitizer<? extends CharSequence, NameSensitive>> desensitizer() default NameDesensitizer.class;

    /**
     * @return 敏感信息在原字符序列中的起始偏移
     */
    int startOffset() default 1;

    /**
     * @return 敏感信息在原字符序列中的结束偏移
     */
    int endOffset() default 0;

    /**
     * @return 正则表达式匹配的敏感信息，如果regexp不为{@code ""}的话则会
     * 忽略{@link NameSensitive#startOffset()}和{@link NameSensitive#endOffset()}的值
     */
    String regexp() default "";

    /**
     * @return 敏感信息替换后的占位符
     */
    char placeholder() default '*';

    /**
     * @return 是否需要对目标对象进行脱敏的条件
     */
    Class<? extends Condition<? extends CharSequence>> condition() default AlwaysTrue.class;

    class AlwaysTrue implements Condition<CharSequence> {

        @Override
        public boolean required(CharSequence target) {
            return true;
        }
    }
}
