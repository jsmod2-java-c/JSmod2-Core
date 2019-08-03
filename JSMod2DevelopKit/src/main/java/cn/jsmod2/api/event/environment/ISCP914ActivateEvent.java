/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.environment;

import cn.jsmod2.api.item.KnobSetting;
import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;
import cn.jsmod2.core.math.Vector;


/**
 * @author magiclu550 #(code) jsmod2
 * @author kevinj
 */


public interface ISCP914ActivateEvent extends IEvent {


    IPlayer getUser();


    KnobSetting getKnobSetting();

    void setKnobSetting(KnobSetting knobSetting);

    Object[] getInputs();

    void setInputs(Object[] inputs);

    Vector getIntakePos();

    void setIntakePos(Vector intakePos);

    Vector getOutputPos();

    void setOutputPos(Vector outputPos);

}
