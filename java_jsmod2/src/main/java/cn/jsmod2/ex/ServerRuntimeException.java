package cn.jsmod2.ex;

import cn.jsmod2.log.ErrorLogger;
import cn.jsmod2.log.IErrorLogger;

public abstract class ServerRuntimeException extends RuntimeException {

    private String method;

    private IErrorLogger eLogger = new ErrorLogger();

    public ServerRuntimeException() {
    }

    public ServerRuntimeException(String message) {
        super(message);
        printServerError();
    }

    public ServerRuntimeException(String message, Throwable cause) {
        super(message, cause);
        printServerError();
    }

    public ServerRuntimeException(Throwable cause) {
        super(cause);
        printServerError();
    }

    public ServerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        printServerError();
    }

    public IErrorLogger geteLogger() {
        return eLogger;
    }

    private void printServerError(){
        eLogger.error("Server-Runtime-Exception:"+Thread.currentThread().getName()+":From Server-"+this.getClass());
        eLogger.error("Parent-Exception"+this.getClass());
        eLogger.error("How to fix this exception?"+(method==null?this.getMessage():method));
    }

    protected void initMethodToFixed(String method){
        this.method = method;
    }

}
