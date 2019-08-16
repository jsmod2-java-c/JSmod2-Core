package cn.jsmod2.network.protocol.map.map;



public class AnnounceNtfEntrancePacket extends SetMapPacket {

    public int scpsLeft;
    public int mtfNumber;
    public char mtfLetter;

    public static final int ID = 145;

    public AnnounceNtfEntrancePacket() {
        super(145);
    }

    @Override
    public void send() {
        requester.with("scpsLeft",scpsLeft).with("mtfNumber",mtfNumber).with("mtfLetter",mtfLetter).to();
    }
}
