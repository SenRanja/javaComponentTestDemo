package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.BankCardNumberSensitive;

/**
 * 银行卡号码脱敏器
 *
 * @author 
 */
public class BankCardNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, BankCardNumberSensitive> {

    @Override
    public String desensitize(String target, BankCardNumberSensitive annotation) {
        validateLength(target, annotation); // 校验银行卡号长度
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

    private void validateLength(String target, BankCardNumberSensitive annotation) {
        int length = target.length();
        int minLength = annotation.minLength();
        int maxLength = annotation.maxLength();

        if (length < minLength || length > maxLength) {
            throw new IllegalArgumentException("数据格式错误，数据脱敏失败!");
        }
    }

}
