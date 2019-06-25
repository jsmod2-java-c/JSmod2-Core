package cn.jsmod2.core.log;

/**
 * 规定了debug相关信息，可以规定前缀和后缀
 * @author magiclu550
 */

public interface IDebugLogger {

    void debug(String message);

    void debug(String message,String prefix);

    void debug(String message,String prefix,String suffix);
}
