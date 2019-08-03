package cn.jsmod2.api.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;

public interface IAdminQueryEvent extends IEvent {

    IPlayer getAdmin();

    String getQuery();

    void setQuery(String query);

    String getOutput();

    void setOutput(String output);

    boolean isHandled();

    void setHandled(boolean handled);

    boolean isSuccessful();

    void setSuccessful(boolean successful);

}
