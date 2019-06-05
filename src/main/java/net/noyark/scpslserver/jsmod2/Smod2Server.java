package net.noyark.scpslserver.jsmod2;

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
