package cn.jsmod2.core;


public class Application {

    private static boolean started;

    public static void run(Class<? extends Server> serverType,Class<?> clz,String[] args){
        try {
            if (!started) {
                serverType.newInstance().start(clz, args);
                started = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
