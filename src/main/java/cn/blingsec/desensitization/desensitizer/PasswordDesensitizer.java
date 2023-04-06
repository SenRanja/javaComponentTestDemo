package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.PasswordSensitive;

/**
 * 密码脱敏器
 *
 * @author 
 */
public class PasswordDesensitizer extends AbstractCharSequenceDesensitizer<String, PasswordSensitive> {

    @Override
    public String desensitize(String target, PasswordSensitive annotation) {
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

}
