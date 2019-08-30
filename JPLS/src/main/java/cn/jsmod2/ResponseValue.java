/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

/**
 * 用于web中使用，主要和前段的ajax进行异步的调用，在返回到客户端时，
 * 是以json字符串的形式返回
 * @param <T> value的类型
 * @author magiclu550
 */
public class ResponseValue<T> {

    /**
     * 行为返回值
     */
    private String message;

    /**
     * 返回的结果对象
     */
    private T value;

    /**
     * 状态码
     */
    private int state;

    public ResponseValue(String message, T responseValue, int state) {
        this.message = message;
        this.value = responseValue;
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
