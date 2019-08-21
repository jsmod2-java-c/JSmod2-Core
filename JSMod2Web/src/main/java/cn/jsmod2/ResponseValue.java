package cn.jsmod2;

public class ResponseValue<T> {

    private String message;

    private T responseValue;

    private int state;

    public ResponseValue(String message, T responseValue, int state) {
        this.message = message;
        this.responseValue = responseValue;
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(T responseValue) {
        this.responseValue = responseValue;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
