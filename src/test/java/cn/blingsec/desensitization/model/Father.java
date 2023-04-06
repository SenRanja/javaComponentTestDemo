package cn.blingsec.desensitization.model;

import cn.blingsec.desensitization.annotation.ChineseNameSensitive;
import cn.blingsec.desensitization.annotation.CommonOmissionSensitive;
import cn.blingsec.desensitization.annotation.PhoneNumberSensitive;

/**
 * @author 
 */
public class Father extends Parent {

    @ChineseNameSensitive
    private String name = "李强";

    @PhoneNumberSensitive
    private String phoneNumber = "12345678911";

    @CommonOmissionSensitive
    private String idCardNumber = "321181199301096001";

    @Override
    public String toString() {
        return "Father{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                '}';
    }
}
