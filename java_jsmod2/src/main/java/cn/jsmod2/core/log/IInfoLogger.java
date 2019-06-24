package cn.jsmod2.core.log;

public interface IInfoLogger {

    void info(String message,String prefix);

    void info(String message);

    void info(String message,String prefix,String suffix);
}
