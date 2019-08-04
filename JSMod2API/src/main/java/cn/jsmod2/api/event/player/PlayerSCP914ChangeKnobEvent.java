/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;


import cn.jsmod2.api.item.KnobSetting;
import cn.jsmod2.api.player.Player;

/**
 * @author kevinj
 */
public class PlayerSCP914ChangeKnobEvent extends PlayerEvent implements IPlayerSCP914ChangeKnobEvent{
    private KnobSetting knobSetting;

    public KnobSetting getKnobSetting() {
        return knobSetting;
    }

    public void setKnobSetting(KnobSetting knobSetting) {
        this.knobSetting = knobSetting;
    }

    public PlayerSCP914ChangeKnobEvent(){

    }

    public PlayerSCP914ChangeKnobEvent(Player player, KnobSetting knobSetting) {
        super(player);
        this.knobSetting = knobSetting;
    }
}
