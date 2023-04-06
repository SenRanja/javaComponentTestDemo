package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.CommonOmissionSensitive;

public class CommonOmissionDesensitizer extends AbstractCharSequenceDesensitizer<String, CommonOmissionSensitive> {

    @Override
    public String desensitize(String target, CommonOmissionSensitive annotation) {
//        validateLength(target, annotation);
//        int length = target.length();
//        int startOffset = length / 3;
//        int endOffset = length - startOffset;
//        int startOffset = 3;
//        int endOffset = 3;
//        for (int i = 2; i < nameLength; i++) {
//            desensitized.setCharAt(i, annotation.placeholder());
//        }
//        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, startOffset, endOffset, annotation.placeholder())) : target;
//        if (!required(target, annotation.condition())) {
//            return target;
//        }

        validateLength(target, annotation);

        int length = target.length();
        StringBuilder desensitized = new StringBuilder(target);
        int startOffset = length / 3;
        int endOffset = (length / 3)*2;


        for (int i = 0; i < startOffset; i++) {
            desensitized.setCharAt(i, annotation.placeholder());
        }
        for (int i = endOffset; i < length; i++) {
            desensitized.setCharAt(i, annotation.placeholder());
        }

        return desensitized.toString();
    }

    private void validateLength(String target, CommonOmissionSensitive annotation) {
        int length = target.length();
        if (length < 3) {
            throw new IllegalArgumentException("数据格式错误，数据脱敏失败!");
        }
    }
}
