package cn.jsmod2.log;

public interface IErrorLogger {

    void error(String message);

    void error(String message,String prefix);

    void error(String message,String prefix,String suffix);

}
