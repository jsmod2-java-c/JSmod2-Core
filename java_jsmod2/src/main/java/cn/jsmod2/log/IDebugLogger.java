package cn.jsmod2.log;

public interface IDebugLogger {

    void debug(String message);

    void debug(String message,String prefix);

    void debug(String message,String prefix,String suffix);
}
