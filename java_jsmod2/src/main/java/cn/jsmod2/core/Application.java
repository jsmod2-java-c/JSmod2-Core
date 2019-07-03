package cn.jsmod2.core;


import cn.jsmod2.core.annotations.ServerApplication;

public class Application {

    private static boolean started;

    public static void run(Class<?> clz,String[] args){
        try {
            if (!started) {
                ServerApplication application = clz.getAnnotation(ServerApplication.class);
                application.value().newInstance().start(clz, args);
                started = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void runTest(Class<?> clz,String[] args){
        try{
            ServerApplication application = clz.getAnnotation(ServerApplication.class);
            application.value().newInstance().startWatch(clz, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
