package cn.jsmod2.ex;

public class NoSuchPlayerException extends RuntimeException{

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
