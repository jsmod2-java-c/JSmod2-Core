package cn.jsmod2.api.event.server;

import cn.jsmod2.api.server.IRound;
import cn.jsmod2.api.server.ROUND_END_STATUS;

public interface ICheckRoundEndEvent extends IServerEvent {

    IRound getRound();

    void setStatus(ROUND_END_STATUS stats);

    ROUND_END_STATUS getStatus();
}
