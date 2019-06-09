package cn.jsmod2.test.foundbug.jsmod2.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class Test2 {

    @Test
    public void testJSON(){
        System.out.println(JSON.parseObject("{'x':1,'y':2}",TestObj.class));
    }
}
