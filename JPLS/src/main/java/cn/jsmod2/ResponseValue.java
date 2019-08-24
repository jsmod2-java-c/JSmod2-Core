package cn.jsmod2;

public class ResponseValue<T> {

    private String message;

    private T value;

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
