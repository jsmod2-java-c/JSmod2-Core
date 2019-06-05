package cn.jsmod2.scpslserver.event;

/**
 * @author magiclu550
 */

public abstract class Event {

    private String eventName;

    public final String getEventName() {
        return eventName == null ? this.getClass().getName() : eventName;
    }


}
