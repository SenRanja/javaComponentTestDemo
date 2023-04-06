package cn.blingsec.desensitization.util;

import cn.blingsec.desensitization.exception.DesensitizationException;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author 
 */
public final class UnsafeUtil {

    private static final Unsafe UNSAFE;

    static {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new DesensitizationException(String.format("初始化%s失败", Unsafe.class), e);
        }
    }

    private UnsafeUtil() {
    }

    /**
     * 实例化指定{@link Class}
     *
     * @param clazz 对象的{@link Class}
     * @param <T>   对象的类型
     * @return 指定 {@link Class}的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return (T) UNSAFE.allocateInstance(clazz);
        } catch (InstantiationException e) {
            throw new DesensitizationException(String.format("实例化%s失败", clazz), e);
        }
    }

}
