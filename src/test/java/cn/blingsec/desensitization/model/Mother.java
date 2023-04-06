package cn.blingsec.desensitization.model;

import cn.blingsec.desensitization.annotation.ChineseNameSensitive;
import cn.blingsec.desensitization.annotation.CommonOmissionSensitive;
import cn.blingsec.desensitization.annotation.PhoneNumberSensitive;

/**
 * @author 
 */
public class Mother extends Parent {

    @ChineseNameSensitive(regexp = "婷")
    private String name = "张婷婷";

    @PhoneNumberSensitive
    private String phoneNumber = "12345678912";

    @CommonOmissionSensitive
    private String idCardNumber = "321181199301096002";

    @Override
    public String toString() {
        return "Mother{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                '}';
    }
}
