package cn.jsmod2.log;

public interface IWarnLogger {


    void warn(String message);

    void warn(String message,String prefix);

    void warn(String message,String prefix,String suffix);

}
