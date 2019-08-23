package cn.jsmod2.api.event.config;

public interface ISetConfigEvent {

    String getKey();

    void setKey(String key);

    <T> T getValue(Class<T> type);

    void setValue(Object value);

    <T> T getDefaultValue(Class<T> type);

}