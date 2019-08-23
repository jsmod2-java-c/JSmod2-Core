package cn.jsmod2.ex;

import cn.jsmod2.core.ex.ServerRuntimeException;

public class ApiErrorException extends ServerRuntimeException {

    public ApiErrorException() {
    }

    public ApiErrorException(String message) {
        super(message);
    }

    public ApiErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiErrorException(Throwable cause) {
        super(cause);
    }

    public ApiErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
