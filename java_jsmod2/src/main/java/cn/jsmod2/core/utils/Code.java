/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.utils;

/**
 * 用于函数式编程中，充当扩展代码块
 * 如函数式异常处理
 *
 * @author magiclu550
 */
@FunctionalInterface
public interface Code{

    void code() throws Exception;

}
