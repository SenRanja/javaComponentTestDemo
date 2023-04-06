package cn.blingsec.desensitization.resolver;

/**
 * 能够排序的对象
 *
 * @author 
 */
public interface Sortable {

    /**
     * 最高优先级（最先执行）
     *
     * @see java.lang.Integer#MIN_VALUE
     */
    int HIGHEST_PRIORITY = Integer.MIN_VALUE;

    /**
     * 最低优先级（最晚执行）
     *
     * @see java.lang.Integer#MAX_VALUE
     */
    int LOWEST_PRIORITY = Integer.MAX_VALUE;

    /**
     * 对象的顺序值，较高的顺序值将被解析为较低的优先级。相同的顺序值的对象只会取第一个对象。
     * 例如顺序值较高的{@link TypeResolver}将会比顺序值较低的{@link TypeResolver}晚执行。而两个相同
     * 顺序值的{@link TypeResolver}只会保留第一个解析器。
     *
     * @return 对应的顺序
     */
    int order();
}
