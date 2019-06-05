package cn.jsmod2.scpslserver.event.config;


import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.event.Event;

/**
 * @author magiclu550 #(code) jsmod2
 */


public class SetConfigEvent extends Event {

    private String key;

    private Object value;

    private Object defaultValue;

    public SetConfigEvent(String key, Object value, Object defaultValue) {
        this.key = key;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public SetConfigEvent(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }


    /** java-bean */
    @UseForServerInit
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }


}
