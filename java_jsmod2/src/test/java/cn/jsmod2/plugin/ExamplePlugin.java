package cn.jsmod2.plugin;

import cn.jsmod2.core.annotations.EnableRegister;
import cn.jsmod2.core.annotations.Main;
import cn.jsmod2.core.plugin.PluginBase;
import cn.jsmod2.core.utils.config.Config;
import cn.jsmod2.core.utils.config.ConfigQueryer;
import cn.jsmod2.core.utils.config.ConfigType;

@Main(name = "example")
@EnableRegister
public class ExamplePlugin extends PluginBase {

    @Override
    public void onLoad() {
        try {
            Config config = ConfigQueryer.getInstance(
                    "hello.json", false, ConfigType.JSON);
            config.put("a.b", "11");
            config.save();
            Config config1 = ConfigQueryer.getInstance(
                    "hello.yml", false, ConfigType.YAML);
            config1.put("a.b", "11");
            config1.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}


