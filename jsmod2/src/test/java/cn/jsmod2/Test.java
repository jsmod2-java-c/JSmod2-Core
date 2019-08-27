package cn.jsmod2;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        System.out.println(new File("a/b/c/a.txt").getName());
        System.out.println(new File("a/b/c/a.txt").getName());
        System.out.println(new File("a/b/c/a.txt").getName());
        System.out.println(new File("a/b/c/a.txt").getName());
        System.out.println(1-((double) Runtime.getRuntime().freeMemory()/(double)Runtime.getRuntime().totalMemory()));
        System.out.println(new File("a/b/c/a.txt").getName());
    }
}
