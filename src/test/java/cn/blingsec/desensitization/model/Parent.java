package cn.blingsec.desensitization.model;

import cn.blingsec.desensitization.annotation.NameSensitive;
import cn.blingsec.desensitization.annotation.CommonOmissionSensitive;
import cn.blingsec.desensitization.annotation.PhoneNumberSensitive;

import java.util.StringJoiner;

/**
 * @author 
 */
abstract class Parent {

    @NameSensitive
    private String name = "Parent";

    @PhoneNumberSensitive
    private String phoneNumber = "12345678913";

    @CommonOmissionSensitive
    private String idCardNumber = "321181199301096003";


    @Override
    public String toString() {
        return new StringJoiner(", ", Parent.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("idCardNumber='" + idCardNumber + "'")
                .toString();
    }
}
