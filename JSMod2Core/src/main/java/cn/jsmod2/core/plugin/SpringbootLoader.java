package cn.jsmod2.core.plugin;


import cn.jsmod2.core.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;

public class SpringbootLoader {

    public static void loadBean(Class bean){
        try {
            if(isSpringBeanClass(bean)){
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(bean);
                BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

                beanDefinition.setScope("singleton");

                String beanName = StringUtils.uncapitalize(bean.getSimpleName());

                beanName = beanName.substring(beanName.lastIndexOf(".")+1);

                beanName = StringUtils.uncapitalize(beanName);

                BeanRegister.getRegistry().registerBeanDefinition(beanName,beanDefinition);

            }
        }catch (Exception e){
            Utils.printException(e);
        }
    }

    public static boolean isSpringBeanClass(Class<?> cla){
        if(cla==null){
            return false;
        }
        //是否是接口
        if(cla.isInterface()){
            return false;
        }

        //是否是抽象类
        if( Modifier.isAbstract(cla.getModifiers())){
            return false;
        }

        if(cla.getAnnotation(Component.class)!=null){
            return true;
        }
        if(cla.getAnnotation(Repository.class)!=null){
            return true;
        }
        if(cla.getAnnotation(Service.class)!=null){
            return true;
        }

        return false;
    }


}
