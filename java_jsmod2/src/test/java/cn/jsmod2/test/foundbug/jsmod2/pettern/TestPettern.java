package cn.jsmod2.test.foundbug.jsmod2.pettern;

import org.junit.Test;

public class TestPettern {

    @Test
    public void testPattern(){
        String var = "func name();start:echo(1):end";
        System.out.println(var.matches("func [\\s\\S]+\\(([\\s\\S]*|[\\s\\S]+)\\);start:[\\s\\S]+:end"));
    }

    @Test
    public void testIf(){
        String var="a=0";

        String var1 = "if\\[[\\s\\S]+\\](([\\s\\S]+)|\\{[.]+\\})((elif\\[[\\s\\S]+\\]([\\s\\S]+)|else\\{([\\s\\S]+;)+\\})+|elif\\[[\\s\\S]+\\]\\{([\\s\\S]+)+\\}|)";
        System.out.println(var1);
        System.out.println(var.matches(var1));
        String var2 = "if\\[[\\s\\S]+\\]\\{([\\s\\S]+)\\}";
        System.out.println(var2);
        System.out.println(var.matches(var2));
        String var3 = "if\\[[\\s\\S]+\\]\\{([\\s\\S]+;)+\\}";
        System.out.println(var3);
        System.out.println(var.matches(var3));
        String pattern = "if\\[[.]\\]((\\{([.]+)+\\}|([.]+)))((elif\\[[.]+\\]([.]+)|elif\\s\\[[.]\\]\\{[.]+\\}|else\\{[.]+\\})*|)+";
        System.out.println(pattern);
        System.out.println(var.matches(pattern));
    }

    @Test
    public void test2(){
        String val = "([a-z0-9A-Z_]=)*[\\s\\S]+\\(([\\s\\S]+|[\\s\\S]*)\\)\\{([\\s\\S]+|)\\}";
        System.out.println("if(1){echo(1)}".matches(val));
    }

}
