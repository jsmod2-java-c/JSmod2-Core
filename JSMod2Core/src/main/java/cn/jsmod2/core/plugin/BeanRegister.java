package cn.jsmod2.core.plugin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class BeanRegister implements BeanDefinitionRegistryPostProcessor {

    private static BeanDefinitionRegistry registry;

    private static ConfigurableListableBeanFactory factory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        registry = beanDefinitionRegistry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        factory = configurableListableBeanFactory;
    }

    public static BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    public static ConfigurableListableBeanFactory getFactory() {
        return factory;
    }
}
