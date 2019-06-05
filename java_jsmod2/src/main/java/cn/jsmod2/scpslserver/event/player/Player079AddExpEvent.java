package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.player.ExperienceType;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class Player079AddExpEvent extends PlayerEvent {
    private ExperienceType experienceType;
    private float expToAdd;

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public float getExpToAdd() {
        return expToAdd;
    }

    public void setExpToAdd(float expToAdd) {
        this.expToAdd = expToAdd;
    }


    /** java-bean */
    @UseForServerInit
    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    public Player079AddExpEvent(Player player, ExperienceType experienceType, float expToAdd) {
        super(player);
        this.experienceType = experienceType;
        this.expToAdd = expToAdd;
    }

    public Player079AddExpEvent(){

    }
}
