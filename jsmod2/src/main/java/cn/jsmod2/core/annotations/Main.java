package cn.jsmod2.core.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 最新的插件注解，可以杜绝配置文件的加入
 * @author magiclu550
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Main {

    String name();

    String description() default "jsmod2 plugin";

    String version() default "1.0.0";
}
