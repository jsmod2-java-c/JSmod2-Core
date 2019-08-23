package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.IPlayer;

public interface IPlayerInfectedEvent extends IPlayerEvent {

    float getDamage();

    void setDamage(float damage);

    IPlayer getAttacker();

    float getInfectTime();

    void setInfectTime(float infectTime);

}
