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
 * 在注册插件过程中，如果发现煮类格式错误或不存在，会抛出该异常
 *
 * @author magiclu550
 */

public class MainClassErrorException extends PluginException {

    public MainClassErrorException() {
        super();
    }

    public MainClassErrorException(String message) {
        super(message);
    }
}
