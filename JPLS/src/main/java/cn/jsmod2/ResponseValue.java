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
