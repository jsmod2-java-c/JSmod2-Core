package cn.jsmod2.core.log;
/**
 * 规定了warn相关信息，可以规定前缀和后缀
 * @author magiclu550
 */
public interface IWarnLogger {


    void warn(String message);

    void warn(String message, String prefix);

    void warn(String message, String prefix, String suffix);

    void multiWarn(Class<?> clz,String message,String prefix,String suffix);

}
