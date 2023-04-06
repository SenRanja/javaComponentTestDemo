package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.NameSensitive;

/**
 * 中文名称脱敏器
 *
 * @author 
 */
public class NameDesensitizer extends AbstractCharSequenceDesensitizer<String, NameSensitive> {

    @Override
    public String desensitize(String target, NameSensitive annotation) {
        if (!required(target, annotation.condition())) {
            return target;
        }

        if (isEnglishName(target)) {
            return desensitizeEnglishName(target, annotation);
        } else {
            validateLength(target, annotation);
            return desensitizeChineseName(target, annotation);
        }
    }

    private boolean isEnglishName(String target) {
        return target.matches("[a-zA-Z ]+");
    }

    private String desensitizeEnglishName(String target, NameSensitive annotation) {
        String[] nameParts = target.split("\\s+");
        StringBuilder desensitized = new StringBuilder();

        for (String part : nameParts) {
            desensitized.append(part.charAt(0));
            for (int i = 1; i < part.length(); i++) {
                desensitized.append(annotation.placeholder());
            }
            desensitized.append(" ");
        }

        // Remove the extra space at the end
        desensitized.setLength(desensitized.length() - 1);

        return desensitized.toString();
    }

    private String desensitizeChineseName(String target, NameSensitive annotation) {
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

    private void validateLength(String target, NameSensitive annotation) {
        int length = target.length();
        int minLength = annotation.minLength();

        if (length < minLength) {
            throw new IllegalArgumentException("数据格式错误，数据脱敏失败!");
        }
    }
}

