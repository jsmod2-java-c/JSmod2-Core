package cn.jsmod2.core.annotations;

import cn.jsmod2.core.Start;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 标记启动类的注解，并表明启动Server对象
 *
 * @author magiclu550
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServerApplication {

    Class<? extends Start> value();

}
