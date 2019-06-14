package cn.jsmod2.test.foundbug.jsmod2.pettern;

import org.junit.Test;

public class TestPettern {

    @Test
    public void testPattern(){
        String var = "a=0";
        System.out.println(var.matches("[a-z0-9A-Z_]+=[a-z0-9A-Z_]"));
    }

}
