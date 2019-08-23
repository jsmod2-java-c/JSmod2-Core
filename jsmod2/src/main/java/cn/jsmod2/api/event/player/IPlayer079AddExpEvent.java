package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.ExperienceType;

public interface IPlayer079AddExpEvent extends IPlayerEvent {

    ExperienceType getExperienceType();

    float getExpToAdd();

    void setExpToAdd(float expToAdd);

}
