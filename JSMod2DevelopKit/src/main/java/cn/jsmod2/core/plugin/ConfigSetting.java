package cn.jsmod2.core.plugin;

public class ConfigSetting {

    private String key;

    private String value;

    private String defaultValue;

    private boolean primary;

    private String description;

    public ConfigSetting(String key, String value, String defaultValue,boolean primary) {
        this.key = key;
        this.value = value;
        this.defaultValue = defaultValue;
        this.primary = primary;
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

    public boolean isPrimary() {
        return primary;
    }

    public String getDescription() {
        return description;
    }
}
