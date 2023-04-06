package cn.blingsec.desensitization.model;

import cn.blingsec.desensitization.annotation.BankCardNumberSensitive;
import cn.blingsec.desensitization.annotation.CascadeSensitive;
import cn.blingsec.desensitization.annotation.CharSequenceSensitive;
import cn.blingsec.desensitization.annotation.NameSensitive;
import cn.blingsec.desensitization.annotation.EmailSensitive;
import cn.blingsec.desensitization.annotation.CommonOmissionSensitive;
import cn.blingsec.desensitization.annotation.PasswordSensitive;
import cn.blingsec.desensitization.annotation.PhoneNumberSensitive;
import cn.blingsec.desensitization.annotation.UsccSensitive;
import cn.blingsec.desensitization.desensitizer.Condition;
import cn.blingsec.desensitization.desensitizer.Desensitizer;
import cn.blingsec.desensitization.desensitizer.PhoneNumberDesensitizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 
 */
public class Child<T extends Collection<@EmailSensitive String>> extends Parent {

    @NameSensitive(placeholder = 'x')
    private String name = "李富贵";

    @PhoneNumberSensitive(desensitizer = CustomizedPhoneNumberDesensitizer.class)
    private Long phoneNumber = 12345678910L;

    @CommonOmissionSensitive
    private String idCardNumber = "321181199301096000";

    @UsccSensitive
    private String unifiedSocialCreditCode = "91310106575855456U";

    @CharSequenceSensitive
    private String string = "123456";

    @EmailSensitive
    private String email = "123456@qq.com";

    @PasswordSensitive
    private String password = "123456";

    @BankCardNumberSensitive
    private String bankCardNumber = "6222600260001072444";

    @CascadeSensitive
    private Mother mother = new Mother();

    @CascadeSensitive
    private Father father = new Father();

    private List<@CascadeSensitive Parent> parents1 = Stream.of(new Father(), new Mother(), null).collect(Collectors.toList());

    private List<@EmailSensitive String> emails1 = Stream.of("123456@qq.com", "1234567@qq.com", "1234568@qq.com").collect(Collectors.toList());

    private Map<@NameSensitive String, @EmailSensitive String> emails2 = Stream.of("张三", "李四", "小明").collect(Collectors.toMap(s -> s, s -> "123456@qq.com"));

    private Map<@CascadeSensitive Parent, @EmailSensitive String> parents2 = Stream.of(new Father(), new Mother()).collect(Collectors.toMap(p -> p, p -> "123456@qq.com"));

    private @PasswordSensitive String[] passwords = {"123456", "1234567", "12345678", null};

    private @EmailSensitive String[][] emails3 = {{"123456@qq.com", "1234567@qq.com"}, {"12345678@qq.com"}};

    private @EmailSensitive String[][][] emails4 = {{{"123456@qq.com", "1234567@qq.com"}, {"12345678@qq.com"}}, {{"123456@qq.com"}, {"123456@qq.com"}, {"123456@qq.com"}}};

    private @CascadeSensitive Parent[] parents3 = {new Father(), new Mother()};

    private Map<List<@EmailSensitive String[]>, Map<@CascadeSensitive Parent, List<@EmailSensitive String>[]>> map1 = new HashMap<>();

    @SuppressWarnings("unchecked")
    private T t = (T) Stream.of("123456@qq.com", "1234567@qq.com", "1234568@qq.com").collect(Collectors.toList());

    private List<@CascadeSensitive ? extends Parent> parents = Stream.of(new Father(), new Mother()).collect(Collectors.toList());

    @SuppressWarnings("unchecked")
    private List<? extends T> list = (List<? extends T>) Stream.of(Stream.of("123456@qq.com", "1234567@qq.com", "1234568@qq.com", null).collect(Collectors.toList())).collect(Collectors.toList());

    private List<@CharSequenceSensitive(condition = StringCondition.class) String> condition = Stream.of("1", "2", "3", "4", "5", "6").collect(Collectors.toList());

    // 复杂字段赋值
    {
        // map1
        List<String[]> list = Stream.of(new String[]{"123456@qq.com", null}, new String[]{"1234567@qq.com"}, new String[]{"1234567@qq.com", "12345678@qq.com"}).collect(Collectors.toList());
        @SuppressWarnings("unchecked")
        Map<Parent, List<String>[]> map = Stream.of(new Father(), new Mother()).collect(Collectors.toMap(p -> p, p -> (List<String>[]) new List<?>[]{Stream.of("123456@qq.com", "1234567@qq.com", "1234568@qq.com").collect(Collectors.toList())}));
        map1.put(list, map);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Child.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("phoneNumber=" + phoneNumber)
                .add("idCardNumber='" + idCardNumber + "'")
                .add("unifiedSocialCreditCode='" + unifiedSocialCreditCode + "'")
                .add("string='" + string + "'")
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("bankCardNumber='" + bankCardNumber + "'")
                .add("mother=" + mother)
                .add("father=" + father)
                .add("parents1=" + parents1)
                .add("emails1=" + emails1)
                .add("emails2=" + emails2)
                .add("parents2=" + parents2)
                .add("passwords=" + Arrays.toString(passwords))
                .add("emails3=" + Arrays.toString(emails3))
                .add("emails4=" + Arrays.toString(emails4))
                .add("parents3=" + Arrays.toString(parents3))
                .add("map1=" + map1)
                .add("t=" + t)
                .add("parents=" + parents)
                .add("list=" + list)
                .add("condition=" + condition)
                .toString();
    }

    /**
     * 自定义脱敏器处理数字类型的手机号码，默认的脱敏器只支持处理{@link String}类型的手机号码。
     *
     * @see PhoneNumberDesensitizer
     */
    private static class CustomizedPhoneNumberDesensitizer implements Desensitizer<Long, PhoneNumberSensitive> {

        @Override
        public Long desensitize(Long target, PhoneNumberSensitive annotation) {
            return Long.parseLong(target.toString().replace("4567", "0000"));
        }

    }

    /**
     * 自定义脱敏生效条件
     */
    private static class StringCondition implements Condition<String> {

        @Override
        public boolean required(String target) {
            return !target.equals("1") && !target.equals("3") && !target.equals("5");
        }
    }
}
