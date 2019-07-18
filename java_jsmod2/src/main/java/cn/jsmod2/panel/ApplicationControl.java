package cn.jsmod2.panel;


public class ApplicationControl {

    public static void run(String[] args, Class<?> mainClass){
        RegisterController controller = mainClass.getAnnotation(RegisterController.class);
        if(controller!=null){
            UIMain main = new UIMain(controller.value());

        }
    }
}
