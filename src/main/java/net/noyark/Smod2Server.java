package net.noyark;

//TODO Smod2Server 计划5.15完成


public class Smod2Server {

    /** TODO 通过数据包获取 */
    private String address;

    /** TODO 通过数据包获取 */
    private int port;

    public Smod2Server(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

}
