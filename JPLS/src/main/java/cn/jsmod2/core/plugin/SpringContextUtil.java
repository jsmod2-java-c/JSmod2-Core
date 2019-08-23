package cn.jsmod2.core.plugin;


import cn.jsmod2.core.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void loadBean(Class clz){
        if(isSpringBean(clz)){
            String beanName = StringUtils.uncapitalize(clz.getSimpleName());
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) SpringContextUtil.getApplicationContext();
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)configurableApplicationContext.getBeanFactory();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clz);
            defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
            RequestMappingHandlerMapping mapping = configurableApplicationContext.getBean(RequestMappingHandlerMapping.class);
            Method method = ReflectionUtils.findMethod(RequestMappingHandlerMapping.class,"getMappingForMethod",Method.class,Class.class);
            method.setAccessible(true);
            Method[] methods = clz.getDeclaredMethods();
            for(Method m:methods){
                //RequestMapping("/aa")+RequestMapping("/name");
                RequestMapping rm = m.getAnnotation(RequestMapping.class);
                String[] paths = rm.value();
                try {
                    RequestMappingInfo mappingInfo = (RequestMappingInfo) method.invoke(mapping, m, clz);
                    mapping.registerMapping(mappingInfo, configurableApplicationContext.getBean(beanName), m);
                }catch (Exception e){
                    Utils.printException(e);
                }
            }

        }
    }

    public static boolean isSpringBean(Class clz){
        if(clz == null)return false;
        if(clz.isInterface())return false;
        if(Modifier.isAbstract(clz.getModifiers()))return false;
        if (clz.getAnnotation(Controller.class)!=null)return true;
        if(clz.getAnnotation(RestController.class)!=null)return true;
        if(clz.getAnnotation(Component.class)!=null)return true;
        if(clz.getAnnotation(Repository.class)!=null)return true;
        if(clz.getAnnotation(Service.class)!=null)return true;
        return false;
    }

}
