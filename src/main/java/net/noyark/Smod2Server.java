package net.noyark;

//TODO Smod2Server 计划5.15完成

import java.net.InetAddress;

public class Smod2Server {

    /** TODO 通过数据包获取 */
    private InetAddress address;

    /** TODO 通过数据包获取 */
    private int port;

    public Smod2Server(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

}
