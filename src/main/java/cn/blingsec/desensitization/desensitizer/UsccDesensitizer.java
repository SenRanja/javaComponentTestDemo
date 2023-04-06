package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.UsccSensitive;

/**
 * 统一社会信用代码脱敏器
 *
 * @author 
 */
public class UsccDesensitizer extends AbstractCharSequenceDesensitizer<String, UsccSensitive> {

    @Override
    public String desensitize(String target, UsccSensitive annotation) {
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

}
