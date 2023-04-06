package cn.blingsec.desensitization.annotation;

import cn.blingsec.desensitization.desensitizer.CommonOmissionDesensitizer;
import cn.blingsec.desensitization.desensitizer.Condition;
import cn.blingsec.desensitization.desensitizer.Desensitizer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SensitiveAnnotation
public @interface CommonOmissionSensitive {

    Class<? extends Desensitizer<? extends CharSequence, CommonOmissionSensitive>> desensitizer() default CommonOmissionDesensitizer.class;

    String regexp() default "";

    char placeholder() default '*';

    Class<? extends Condition<? extends CharSequence>> condition() default AlwaysTrue.class;

    class AlwaysTrue implements Condition<CharSequence> {

        @Override
        public boolean required(CharSequence target) {
            return true;
        }
    }
}
