package cn.jsmod2.network.protocol.server;

import cn.jsmod2.api.server.Round;
import cn.jsmod2.core.protocol.GetPacket;

public class GetRoundPacket extends GetPacket {

    public static final int ID = 0x61;

    public GetRoundPacket() {
        super(ID, Round.class);
    }

    @Override
    public Round send() {
        return (Round)(requester.with("field","round").get().get());
    }
}
