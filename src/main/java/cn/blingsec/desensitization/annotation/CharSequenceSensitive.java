package cn.blingsec.desensitization.annotation;

import cn.blingsec.desensitization.desensitizer.Condition;
import cn.blingsec.desensitization.desensitizer.Desensitizer;
import cn.blingsec.desensitization.desensitizer.StringBufferDesensitizer;
import cn.blingsec.desensitization.desensitizer.StringBuilderDesensitizer;
import cn.blingsec.desensitization.desensitizer.StringDesensitizer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link CharSequence}类型对象敏感标记注解。默认的脱敏规则：擦除目标对象中所有的字符，
 * 用户可以基于注解提供的方法自定义脱敏规则。
 * <p><strong>注意：默认的脱敏器是{@link StringDesensitizer}，该脱敏器只会处理{@link String}
 * 类型的对象，如果需要脱敏其它类型的{@link CharSequence}需要自定义相应类型的脱敏器
 * ，然后通过{@link CharSequenceSensitive#desensitizer()}方法表明该类型的脱敏器。</strong></p>
 *
 * @author 
 * @see StringDesensitizer
 * @see StringBuilderDesensitizer
 * @see StringBufferDesensitizer
 */
@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface CharSequenceSensitive {

    /**
     * @return 处理被 {@link CharSequenceSensitive}标记的对象脱敏器，可以自定义子类重写默认的处理逻辑。
     */
    Class<? extends Desensitizer<? extends CharSequence, CharSequenceSensitive>> desensitizer() default StringDesensitizer.class;

    /**
     * @return 敏感信息在原字符序列中的起始偏移
     */
    int startOffset() default 0;

    /**
     * @return 敏感信息在原字符序列中的结束偏移
     */
    int endOffset() default 0;

    /**
     * @return 正则表达式匹配的敏感信息，如果regexp不为{@code ""}的话则会
     * 忽略{@link CharSequenceSensitive#startOffset()}和{@link CharSequenceSensitive#endOffset()}的值
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
