package cn.jsmod2.core.annotations;

import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.event.Listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注册监听器和命令的注解，加载主类上
 * 命令必须有一个构造方法init(Plugin plugin)
 * @author magiclu550
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableRegister {

    //加载命令，默认为true
    boolean command() default true;

    //加载监听器，默认为true
    boolean listener() default true;

    //不自动加载的监听器
    Class<? extends Listener>[] exclusionsListener() default {};

    //不自动加载的指令
    Class<? extends Command>[] exclusionsCommand() default {};

}
