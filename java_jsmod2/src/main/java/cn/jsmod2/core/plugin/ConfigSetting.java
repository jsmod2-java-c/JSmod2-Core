package cn.jsmod2.core.plugin;

public class ConfigSetting {

    private String key;

    private String value;

    private String defaultValue;

    public ConfigSetting(String key, String value, String defaultValue) {
        this.key = key;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
