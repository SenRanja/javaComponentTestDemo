package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.CharSequenceSensitive;

/**
 * {@link String}类型对象脱敏器
 *
 * @author 
 */
public class StringDesensitizer extends AbstractCharSequenceDesensitizer<String, CharSequenceSensitive> {

    @Override
    public String desensitize(String target, CharSequenceSensitive annotation) {
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

}
