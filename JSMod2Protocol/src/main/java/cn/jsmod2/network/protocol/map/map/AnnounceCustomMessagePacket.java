package cn.jsmod2.network.protocol.map.map;


public class AnnounceCustomMessagePacket extends SetMapPacket {

    public static final int ID = 144;

    public String words;

    public AnnounceCustomMessagePacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("words",words).to();
    }
}
