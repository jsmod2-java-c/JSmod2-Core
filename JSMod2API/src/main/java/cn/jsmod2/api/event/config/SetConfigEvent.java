/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.config;


import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.event.Event;

/**
 * @author magiclu550 #(code) jsmod2
 */


public class SetConfigEvent extends Event implements ISetConfigEvent {

    private String key;

    private Object value;

    private Object defaultValue;


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



}
