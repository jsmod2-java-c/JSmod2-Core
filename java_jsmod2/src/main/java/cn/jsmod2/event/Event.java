/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event;

import java.io.Serializable;

/**
 * @author magiclu550
 */

public abstract class Event implements Serializable,Cloneable {

    private String eventName;

    public final String getEventName() {
        return eventName == null ? this.getClass().getName() : eventName;
    }


}
