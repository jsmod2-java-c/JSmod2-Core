package net.noyark.scpslserver.jsmod2.event;

public abstract class Event {

    private String eventName;

    public final String getEventName() {
        return eventName == null ? this.getClass().getName() : eventName;
    }


}
