package cn.jsmod2.panel;

import org.springframework.context.support.GenericApplicationContext;

import java.util.Map;

public class UIMain {

    private Map<String,Object> beans;

    public UIMain(String pack){
        this.beans = scanAll(pack);
        beans.forEach((k,v)->{
            if(v instanceof IUiController){
                ((IUiController) v).init();
            }
        });
    }

    private Map<String,Object> scanAll(String pack){
        GenericApplicationContext context = new GenericApplicationContext();
        UIRegister register = new UIRegister(context);
        register.registerTypeFilter();
        register.scan(pack);
        context.refresh();
        return context.getBeansWithAnnotation(UIController.class);
    }


    //获取bean
    public <T> T getBean(String id,Class<T> type){
        return type.cast(beans.get(id));
    }

    public Map<String, Object> getBeans() {
        return beans;
    }
}
