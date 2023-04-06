package cn.blingsec.desensitization;

import cn.blingsec.desensitization.annotation.*;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import cn.blingsec.desensitization.support.TypeToken;

/**
 * @author 
 */
public class DesensitizationTest {

    /**
     * 根据{@link TypeToken}脱敏对象
     */
    @Test
    public void desensitizeAccordingToTypeToken() {
        desensitize();
    }

    private static void desensitize() {

        // email
        String email_raw = "123456@qq.com";
        JsonObject email = Sensitive.desensitize(email_raw, new TypeToken<@EmailSensitive String>() {
        });
        System.out.println("演示邮箱脱敏 "+email_raw);
        System.out.println(email);

        // LandLineNumber
        System.out.println("演示固定电话脱敏");
        String LandLineNumber1_raw = "(555) 123-4567";
        JsonObject landLineNumber1 = Sensitive.desensitize(LandLineNumber1_raw, new TypeToken<@LandLinePhoneSensitive String>() {
        });
        System.out.println("测试固定电话: "+LandLineNumber1_raw);
        System.out.println(landLineNumber1);
        String LandLineNumber2_raw = "86-10-66778899";
        JsonObject landLineNumber2 = Sensitive.desensitize(LandLineNumber2_raw, new TypeToken<@LandLinePhoneSensitive String>() {
        });
        System.out.println("测试固定电话: "+LandLineNumber2_raw);
        System.out.println(landLineNumber2);

        // bankId
        JsonObject bankId = Sensitive.desensitize("4926309485126374", new TypeToken<@BankCardNumberSensitive String>() {
        });
        System.out.println("演示银行卡脱敏");
        System.out.println(bankId);
        // bankId too long
        String bankId_too_long_raw = "4926302222229485126374";
        JsonObject bankId_too_long = Sensitive.desensitize(bankId_too_long_raw, new TypeToken<@BankCardNumberSensitive String>() {
        });
        System.out.println("演示银行卡数据过长脱敏 "+bankId_too_long_raw);
        System.out.println(bankId_too_long);
        // Name
        JsonObject name0 = Sensitive.desensitize("三", new TypeToken<@ChineseNameSensitive String>() {
        });
        JsonObject name1 = Sensitive.desensitize("张三", new TypeToken<@ChineseNameSensitive String>() {
        });
        JsonObject name2 = Sensitive.desensitize("张某三", new TypeToken<@ChineseNameSensitive String>() {
        });
        JsonObject name3 = Sensitive.desensitize("阿不都沙拉克", new TypeToken<@ChineseNameSensitive String>() {
        });
        System.out.println("演示姓名脱敏");
        System.out.println(name0);
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);

        System.out.println("演示身份证号脱敏");
        String idcard1_raw = "141181199904230063";
        System.out.println("测试用身份证号:"+idcard1_raw);
        JsonObject idcard1 = Sensitive.desensitize(idcard1_raw, new TypeToken<@IdCardNumberSensitive String>() {
        });
        System.out.println(idcard1);
        String idcard2_raw = "1411811999042300623";
        System.out.println("测试用身份证号:"+idcard2_raw);
        JsonObject idcard2 = Sensitive.desensitize(idcard2_raw, new TypeToken<@IdCardNumberSensitive String>() {
        });
        System.out.println(idcard2);

        System.out.println("演示邮箱脱敏");
        String email1_raw = "shenyanjian@gmail.com";
        System.out.println("测试用邮箱:"+email1_raw);
        JsonObject email1 = Sensitive.desensitize(email1_raw, new TypeToken<@EmailSensitive String>() {
        });
        System.out.println(email1);
        String email2_raw = "l@com";
        System.out.println("测试用邮箱:"+email2_raw);
        JsonObject email2 = Sensitive.desensitize(email2_raw, new TypeToken<@EmailSensitive String>() {
        });
        System.out.println(email2);
        String email3_raw = "l.com";
        System.out.println("测试用邮箱:"+email3_raw);
        JsonObject email3 = Sensitive.desensitize(email3_raw, new TypeToken<@EmailSensitive String>() {
        });
        System.out.println(email3);

        System.out.println("演示缺省值脱敏");
        String omission1_raw = "shenyanjian@gmail.com";
        System.out.println("测试:"+omission1_raw);
        JsonObject omission1 = Sensitive.desensitize(omission1_raw, new TypeToken<@CommonOmissionSensitive String>() {
        });
        System.out.println(omission1);
        String omission2_raw = "sm";
        System.out.println("测试:"+omission2_raw);
        JsonObject omission2 = Sensitive.desensitize(omission2_raw, new TypeToken<@CommonOmissionSensitive String>() {
        });
        System.out.println(omission2);

        System.out.println("演示手机号脱敏");
        String tel1_raw = "18536864913";
        System.out.println("测试: 大陆手机"+tel1_raw);
        JsonObject tel1 = Sensitive.desensitize(tel1_raw, new TypeToken<@PhoneNumberSensitive String>() {
        });
        System.out.println(tel1);
        String tel2_raw = "90888812";
        System.out.println("测试: 港澳手机"+tel2_raw);
        JsonObject tel2 = Sensitive.desensitize(tel2_raw, new TypeToken<@PhoneNumberSensitive String>() {
        });
        System.out.println(tel2);
        String tel3_raw = "908888123";
        System.out.println("测试: 台湾手机"+tel3_raw);
        JsonObject tel3 = Sensitive.desensitize(tel3_raw, new TypeToken<@PhoneNumberSensitive String>() {
        });
        System.out.println(tel3);
        String tel4_raw = "908888123222222";
        System.out.println("测试: 其他地区手机"+tel4_raw);
        JsonObject tel4 = Sensitive.desensitize(tel4_raw, new TypeToken<@PhoneNumberSensitive String>() {
        });
        System.out.println(tel4);


        Assert.assertTrue(true);
    }

}
