package cn.jsmod2.test.foundbug.jsmod2.pettern;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestPettern {

    @Test
    public void testPattern(){
        String var = "func name();start:echo(1):end";
        System.out.println(var.matches("func [\\s\\S]+\\(([\\s\\S]*|[\\s\\S]+)\\);start:[\\s\\S]+:end"));
    }

    @Test
    public void testIf() {

        int sum = 909;//4
        if(sum%9==0){
            System.out.println(9);
        }else{
            System.out.println(sum);
        }
    }

    @Test
    public void test3(){
        System.out.println("'你好'".matches("'[\\s\\S]+'"));
    }

    @Test
    public void test4()
    {
        String[] args = {"'s","ssd","we'","sdd"};


        //System.out.println(lists);
        System.out.println(Arrays.toString(args));
    }
}
