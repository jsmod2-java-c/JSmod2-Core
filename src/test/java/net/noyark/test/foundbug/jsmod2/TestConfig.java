package net.noyark.test.foundbug.jsmod2;

import net.noyark.scpslserver.jsmod2.utils.config.Config;
import net.noyark.scpslserver.jsmod2.utils.config.JsonConfig;
import org.junit.Test;

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
}
