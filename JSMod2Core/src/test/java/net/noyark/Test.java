package net.noyark;


import cn.jsmod2.core.math.Vector;
import com.alibaba.fastjson.JSON;

public class Test {



    public static void main(String[] args) {
        System.out.println(JSON.parseArray("[{\"x\":1.0,\"y\":2.1,\"z\":3.2},{\"x\":1.0,\"y\":2.1,\"z\":3.3}]", Vector.class));
    }
}
