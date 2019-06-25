/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.ex;

/**
 * 当查询插件名称时，发现插件不存在，会抛出该异常
 * @author magiclu550
 */

public class NoSuchPluginNameException extends ServerRuntimeException{

    public NoSuchPluginNameException() {
    }

    public NoSuchPluginNameException(String name) {
        super("no such plugin named "+name);
    }
}
