package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.utils.config.Config;
import cn.jsmod2.utils.config.JsonConfig;
import cn.jsmod2.utils.config.PropertiesConfig;
import cn.jsmod2.utils.config.YamlConfig;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * jsmod2 config test
 */

public class TestConfig {

    @Test
    public void json() throws IOException {
        Config config = new JsonConfig("../name.json",false);
        config.put("a.b.hello",new int[]{1,2,3,4});
        config.save();
        System.out.println(Arrays.toString(config.getArray("a.b.hello")));
    }

    @Test
    public void properties() throws IOException{
        Config config = new PropertiesConfig("../test.properties",false);
        config.put("cn.ol","test");
        config.save();
        System.out.println(config.get("cn.ol"));
    }

    @Test
    public void yaml() throws IOException{
        YamlConfig config = new YamlConfig("test.yml",false);
        config.put("a.b.c.d",new int[]{1,2,3,4,5});
        config.save();
        System.out.println(config.get(".",new FileInputStream("test.yml")).getClass());
    }
}
