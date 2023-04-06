package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.BankCardNumberSensitive;
import cn.blingsec.desensitization.annotation.ChineseNameSensitive;

/**
 * 中文名称脱敏器
 *
 * @author 
 */
public class ChineseNameDesensitizer extends AbstractCharSequenceDesensitizer<String, ChineseNameSensitive> {

    @Override
    public String desensitize(String target, ChineseNameSensitive annotation) {
        if (!required(target, annotation.condition())) {
            return target;
        }

        validateLength(target, annotation);

        int nameLength = target.length();
        StringBuilder desensitized = new StringBuilder(target);

        if (nameLength == 2) {
            desensitized.setCharAt(1, annotation.placeholder());
        } else if (nameLength == 3) {
            desensitized.setCharAt(1, annotation.placeholder());
        } else if (nameLength > 3) {
            for (int i = 2; i < nameLength; i++) {
                desensitized.setCharAt(i, annotation.placeholder());
            }
        }

        return desensitized.toString();
    }

    private void validateLength(String target, ChineseNameSensitive annotation) {
        int length = target.length();
        int minLength = annotation.minLength();

        if (length < minLength) {
            throw new IllegalArgumentException("数据格式错误，数据脱敏失败!");
        }
    }
}

