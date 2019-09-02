package cn.jsmod2.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认的前置插件库
 * 如果加了这个注解,将会先去加载之前这个name的依赖包
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadBefore {

    String[] pluginName();//插件名称,不是文件名

}
