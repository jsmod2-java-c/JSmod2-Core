/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.log;

/**
 * 规定了error相关信息，可以规定前缀和后缀
 * @author magiclu550
 */

public interface IErrorLogger {
    void error(String message);

    void error(String message,String prefix);

    void error(String message,String prefix,String suffix);
}
