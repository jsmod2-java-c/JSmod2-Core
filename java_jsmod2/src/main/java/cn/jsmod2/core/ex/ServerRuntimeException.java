package cn.jsmod2.core.ex;

import cn.jsmod2.Register;
import cn.jsmod2.core.RegisterTemplate;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.IErrorLogger;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.protocol.DataPacket;

import java.util.HashMap;
import java.util.Map;

public class ServerRuntimeException extends RuntimeException {

    private String method;

    private IErrorLogger eLogger = ServerLogger.getLogger();

    public ServerRuntimeException() {
        printServerError();
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
        Map<Class<? extends Exception>,String>  exs = new HashMap<>();
        for(RegisterTemplate template: Server.getSender().getServer().getRegisters()) {
            exs.putAll(template.getEx_methods());
        }
        initMethodToFixed(exs.get(this.getClass()));
        eLogger.error("Server-Runtime-Exception:"+Thread.currentThread().getName()+":From Server-"+this.getClass());
        eLogger.error("Parent-Exception"+this.getClass().getSuperclass());
        eLogger.error("How to fix this exception?"+(method==null?this.getMessage():method));
    }

    protected void initMethodToFixed(String method){
        this.method = method;
    }

}
