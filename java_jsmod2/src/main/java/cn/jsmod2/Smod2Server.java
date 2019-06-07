/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2;

//TODO Smod2Server 计划5.15完成




public class Smod2Server extends CommandSender {

    /** TODO 通过数据包获取 */
    private String address;

    /** TODO 通过数据包获取 */
    private int port;

    public Smod2Server() {
        super("CONSOLE","all","console","admin","player","nobody");
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public Server getRuntimeServer(){
        return Server.getSender().getServer();
    }


}
