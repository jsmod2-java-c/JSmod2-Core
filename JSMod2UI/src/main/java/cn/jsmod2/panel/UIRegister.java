package cn.jsmod2.panel;


import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * 针对的扫描框架，注册ui
 */
public class UIRegister extends ClassPathBeanDefinitionScanner {

    public UIRegister(BeanDefinitionRegistry registry) {
        // 父类构造函数第二个参数代表是否使用默认的过滤器，这里我们不需要则设置为false
        super(registry, false);
    }

    public void registerTypeFilter(){
        // 调用父类的addIncludeFilter方法，添加@Service注解过滤器
        addIncludeFilter(new AnnotationTypeFilter(UIController.class));
    }
}
