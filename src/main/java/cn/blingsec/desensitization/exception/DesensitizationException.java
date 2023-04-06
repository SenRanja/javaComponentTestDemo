package cn.blingsec.desensitization.exception;

import java.util.function.Supplier;

/**
 * 脱敏过程中发生的异常
 *
 * @author 
 */
public class DesensitizationException extends RuntimeException implements Supplier<DesensitizationException> {

    public DesensitizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DesensitizationException(String message) {
        super(message);
    }

    @Override
    public DesensitizationException get() {
        return this;
    }
}
