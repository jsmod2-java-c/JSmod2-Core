package cn.jsmod2.core.plugin;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * 用于存放对象,并且动态注入
 */
public class JSmod2ObjectContext {

    private Map<String,Object> beans = new HashMap<>();

    public void registerBean(String name,Object bean){
        if(name.equals("")){
            beans.put(StringUtils.uncapitalize(bean.getClass().getName()),bean);
        }else{
            beans.put(name, bean);
        }
    }

    public void registerBean(Object bean){
        registerBean("",bean);
    }

    public <T> T getBean(String name,Class<T> type){
        if(beans.get(name) == null)throw new NoSuchBeanDefinitionException("no such bean named: "+name);
        return type.cast(beans.get(name));
    }

    public <T> T getBean(Class<T> type){
        for(Map.Entry<String,Object> obj:beans.entrySet()){
            if(type.isInstance(obj.getValue())){
                return type.cast(obj.getValue());
            }
        }
        throw new NoSuchBeanDefinitionException("no such bean type: "+type.getName());
    }

    public Collection<Object> getBeans(){
        return beans.values();
    }
}
