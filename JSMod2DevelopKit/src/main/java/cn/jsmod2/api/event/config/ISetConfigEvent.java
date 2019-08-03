package cn.jsmod2.api.event.config;

public interface ISetConfigEvent {

    String getKey();

    void setKey(String key);

    Object getValue();

    void setValue(Object value);

    Object getDefaultValue();

}
