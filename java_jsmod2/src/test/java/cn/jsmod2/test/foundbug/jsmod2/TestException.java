package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.ex.TypeErrorException;
import cn.jsmod2.log.ServerLogger;
import org.junit.Test;

public class TestException {

    @Test
    public void testEx(){
        throw new TypeErrorException("hello,world");
    }

    @Test
    public void testLog(){
        ServerLogger.getLogger().info("111");
    }
}
