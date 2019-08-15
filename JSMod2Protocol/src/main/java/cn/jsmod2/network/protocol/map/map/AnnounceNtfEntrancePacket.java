package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public class AnnounceNtfEntrancePacket extends SetPacket {

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
