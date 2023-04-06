package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.CharSequenceSensitive;

/**
 * {@link StringBuffer}类型对象脱敏器
 *
 * @author 
 */
public class StringBufferDesensitizer extends AbstractCharSequenceDesensitizer<StringBuffer, CharSequenceSensitive> {

    @Override
    public StringBuffer desensitize(StringBuffer target, CharSequenceSensitive annotation) {
        return required(target, annotation.condition()) ? new StringBuffer().append(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

}
