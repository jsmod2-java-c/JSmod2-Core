package cn.jsmod2.test.foundbug.jsmod2;


public class Test2 extends JDK8Interf {

    public static void main(String[] args) {
        test();
        System.out.println(JDK8Interf.a);
    }
    static void test(){
        Test2.a=1;
    }
}
