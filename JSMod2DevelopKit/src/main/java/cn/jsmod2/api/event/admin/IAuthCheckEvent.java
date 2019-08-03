package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.server.AuthType;
import cn.jsmod2.core.interapi.event.IEvent;

public interface IAuthCheckEvent extends IEvent {

    IPlayer getRequester();

    AuthType getType();

    void setType(AuthType type);

    boolean isAllow();

    void setAllow(boolean allow);

    boolean isSuccessful();

    void setSuccessful(boolean successful);

}
