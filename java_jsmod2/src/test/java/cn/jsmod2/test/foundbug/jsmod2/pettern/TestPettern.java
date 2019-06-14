package cn.jsmod2.test.foundbug.jsmod2.pettern;

import org.junit.Test;

public class TestPettern {

    @Test
    public void testPattern(){
        String var = "func name();start:echo(1):end";
        System.out.println(var.matches("func [\\s\\S]+\\(([\\s\\S]*|[\\s\\S]+)\\);start:[\\s\\S]+:end"));
    }

}
