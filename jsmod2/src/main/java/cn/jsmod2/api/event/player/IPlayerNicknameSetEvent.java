package cn.jsmod2.api.event.player;

public interface IPlayerNicknameSetEvent extends IPlayerEvent{

    String getNickname();

    void setNickname(String nickname);

}
