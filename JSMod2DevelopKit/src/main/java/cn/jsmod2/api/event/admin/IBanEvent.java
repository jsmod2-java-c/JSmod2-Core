package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;

public interface IBanEvent extends IEvent {


    IPlayer getPlayer();

    IPlayer getAdmin();

    int getDuration();

    void setDuration(int duration);

    String getReason();

    void setReason(String reason);

    String getResult();

    void setResult(String result);

    boolean isAllowBan();

    void setAllowBan(boolean allowBan);
}
