package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.CharSequenceSensitive;

/**
 * {@link StringBuilder}类型对象脱敏器
 *
 * @author 
 */
public class StringBuilderDesensitizer extends AbstractCharSequenceDesensitizer<StringBuilder, CharSequenceSensitive> {

    @Override
    public StringBuilder desensitize(StringBuilder target, CharSequenceSensitive annotation) {
        return required(target, annotation.condition()) ? new StringBuilder().append(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

}
