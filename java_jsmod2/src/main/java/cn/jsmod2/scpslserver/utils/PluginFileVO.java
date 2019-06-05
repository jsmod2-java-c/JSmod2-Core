package cn.jsmod2.scpslserver.utils;

public class PluginFileVO {

    private String pluginName;

    private String main_class;

    private String description;

    private String version;

    public PluginFileVO(String pluginName, String main_class, String description, String version) {
        this.pluginName = pluginName;
        this.main_class = main_class;
        this.description = description;
        this.version = version;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getMain_class() {
        return main_class;
    }

    public void setMain_class(String main_class) {
        this.main_class = main_class;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
