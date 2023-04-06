package cn.blingsec.desensitization.desensitizer;

import cn.blingsec.desensitization.annotation.EmailSensitive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱脱敏器
 *
 * @author
 */
public class EmailDesensitizer extends AbstractCharSequenceDesensitizer<String, EmailSensitive> {

    @Override
    public String desensitize(String target, EmailSensitive annotation) {
        if (required(target, annotation.condition())) {
            if (isValidEmail(target)) {
                return customDesensitize(target);
            } else {
                throw new IllegalArgumentException("数据格式错误，数据脱敏失败！");
            }
        }
        return target;
    }

    private boolean isValidEmail(String email) {

        Pattern pattern = Pattern.compile("[\\w.]+@[\\w.]+");
        Matcher matcher = pattern.matcher(email);
        boolean res = matcher.matches();
        return res;
    }

    private String customDesensitize(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex > 3) {
            return email.substring(0, 3) + "***" + email.substring(atIndex);
        } else {
            return email.substring(0, atIndex) + "***" + email.substring(atIndex);
        }
    }

}
