package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.LandLinePhoneSensitive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码脱敏器
 *
 * @author 
 */
public class LandLinePhoneNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, LandLinePhoneSensitive> {

    @Override
    public String desensitize(String target, LandLinePhoneSensitive annotation) {
        validateLength(target, annotation);
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

    private void validateLength(String target, LandLinePhoneSensitive annotation) {
        Pattern pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{5,}$");
        Matcher matcher = pattern.matcher(target);
        boolean res = matcher.matches();

        if (!res) {
            throw new IllegalArgumentException("数据格式错误，数据脱敏失败!");
        }
    }

}
