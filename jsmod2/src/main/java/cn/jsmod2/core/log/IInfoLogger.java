package cn.jsmod2.core.log;
/**
 * 规定了info相关信息，可以规定前缀和后缀
 * @author magiclu550
 */
public interface IInfoLogger {

    void info(String message, String prefix);

    void info(String message);

    void info(String message, String prefix, String suffix);

    void multiInfo(Class<?> clz,String message,String prefix,String suffix);
}
