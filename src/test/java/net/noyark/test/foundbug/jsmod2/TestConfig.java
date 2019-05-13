package net.noyark.test.foundbug.jsmod2;

import net.noyark.scpslserver.jsmod2.utils.config.*;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.Arrays;

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
        System.out.println(config.get("a.b.c.d"));
    }
}
