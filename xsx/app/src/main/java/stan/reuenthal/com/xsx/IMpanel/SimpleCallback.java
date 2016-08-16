package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
/**
 * 简单的回调接口
 */
public interface SimpleCallback<T> {

    /**
     * 回调函数返回结果
     *
     * @param success 是否成功，结果是否有效
     * @param result  结果
     */
    void onResult(boolean success, T result);
}
