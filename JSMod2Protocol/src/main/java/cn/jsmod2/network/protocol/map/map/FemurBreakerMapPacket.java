package cn.jsmod2.network.protocol.map.map;



public class FemurBreakerMapPacket extends SetMapPacket {


    public static final int ID = 150;

    public boolean enable;

    public FemurBreakerMapPacket(){
        super(ID);
    }

    @Override
    public void send() {
        requester.with("enable",enable).to();
    }
}
