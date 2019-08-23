/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;

/**
 * 存储正在运行中的服务器，未来将会做成一个队列
 *
 * @author magiclu550
 */

public class RuntimeServer {

    private Server runtime;

    protected Runtime runtimeInfo = Runtime.getRuntime();

    public RuntimeServer(Server runtime){
        this.runtime = runtime;
    }

    public Server getServer(){
        return runtime;
    }

    public Runtime getRuntimeInfo() {
        return runtimeInfo;
    }
}
