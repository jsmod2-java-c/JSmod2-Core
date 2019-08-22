package cn.jsmod2.api.event.player;

import cn.jsmod2.network.PacketSender;

public class PlayerNicknameSetEvent extends PlayerEvent implements IPlayerNicknameSetEvent {

    private String nickName;

    @Override
    public void setNickname(String s) {
        PacketSender.sendEventSetPacket(playerName,"NickName",nickName);
        this.nickName = s;
    }

    @Override
    public String getNickname() {
        nickName = PacketSender.sendEventGetPacket(playerName,"NickName",String.class);
        return nickName;
    }
}
