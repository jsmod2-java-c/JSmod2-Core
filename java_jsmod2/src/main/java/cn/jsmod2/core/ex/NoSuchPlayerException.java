package cn.jsmod2.core.ex;

public class NoSuchPlayerException extends ServerRuntimeException{
    public NoSuchPlayerException() {
        super();
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
