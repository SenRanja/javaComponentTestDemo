package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.Sensitive;
import cn.blingsec.desensitization.annotation.CommonOmissionSensitive;
import cn.blingsec.desensitization.annotation.PhoneNumberSensitive;
import cn.blingsec.desensitization.support.TypeToken;

public class PhoneNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, PhoneNumberSensitive> {

    @Override
    public String desensitize(String target, PhoneNumberSensitive annotation) {
        int startOffset = 0, endOffset=0;
        PhoneNumberSensitive.Region region = detectRegion(target);

        switch (region) {
            case MAINLAND:
                startOffset = 3;
                endOffset = 4;
                break;
            case HONGKONG_MACAU:
                startOffset = 2;
                endOffset = 2;
                break;
            case TAIWAN:
                startOffset = 2;
                endOffset = 3;
                break;
            case OTHERS:
                int length = target.length();
                StringBuilder desensitized = new StringBuilder(target);
                startOffset = length / 3;
                endOffset = (length / 3)*2;
                for (int i = 0; i < startOffset; i++) {
                    desensitized.setCharAt(i, annotation.placeholder());
                }
                for (int i = endOffset; i < length; i++) {
                    desensitized.setCharAt(i, annotation.placeholder());
                }
                return desensitized.toString();
        }

        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, startOffset, endOffset, annotation.placeholder())) : target;
    }

    private PhoneNumberSensitive.Region detectRegion(String phoneNumber) {
        if (phoneNumber.matches("^1\\d{10}$")) {
            return PhoneNumberSensitive.Region.MAINLAND;
        } else if (phoneNumber.matches("^[96]\\d{7}$")) {
            return PhoneNumberSensitive.Region.HONGKONG_MACAU;
        } else if (phoneNumber.matches("^9\\d{8}$")) {
            return PhoneNumberSensitive.Region.TAIWAN;
        }
        return PhoneNumberSensitive.Region.OTHERS;
    }
}
