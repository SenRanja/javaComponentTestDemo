package cn.blingsec.desensitization.support;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 用来获取对象运行时具体类型的帮助类。由于Java泛型擦除机制，如果我们想获取{@code List<String>}这个对象运行时的泛型类型，
 * 这几乎是很难做到的。而利用{@link TypeToken}我们只需要构造一个它的匿名子类，就能获取这个泛型：
 * <pre>
 *     TypeToken&lt;List&lt;String&gt;&gt; stringList = new TypeToken&lt;List&lt;String&gt;&gt;(){};
 *     stringList.getType() =&gt; java.util.List&lt;java.lang.String&gt;
 * </pre>
 *
 * @param <T> 需要捕获的明确类型
 * @author 
 */
public abstract class TypeToken<T> extends TypeCapture<T> {

    /**
     * {@link T}运行时的类型
     */
    private final Type type;

    /**
     * {@link T}运行时被注解的类型
     */
    private final AnnotatedType annotatedType;

    protected TypeToken() {
        annotatedType = capture();
        type = annotatedType.getType();
    }

    private TypeToken(AnnotatedType annotatedType) {
        this.annotatedType = annotatedType;
        this.type = annotatedType.getType();
    }

    /**
     * 通过已知对象的{@link AnnotatedType}实例化{@link TypeToken}
     *
     * @param annotatedType 对象的{@link AnnotatedType}
     * @param <T>           对象的运行时类型
     * @return 该对象的 {@link TypeToken}
     */
    public static <T> TypeToken<T> of(AnnotatedType annotatedType) {
        return new TypeToken<T>(annotatedType) {
        };
    }

    /**
     * @return 对象运行时的类型
     */
    public final Type getType() {
        return type;
    }

    /**
     * @return 对象运行时被注解的类型
     */
    public final AnnotatedType getAnnotatedType() {
        return annotatedType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeToken<?> typeToken = (TypeToken<?>) o;
        return type.equals(typeToken.type) &&
                annotatedType.equals(typeToken.annotatedType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, annotatedType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TypeToken.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("annotatedType=" + annotatedType)
                .toString();
    }

}
