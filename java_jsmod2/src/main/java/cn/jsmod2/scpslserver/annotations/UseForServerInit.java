package cn.jsmod2.scpslserver.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 表明某些方法只是用于服务器初始化使用，是不得被插件调用的
 * @author magiclu550
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseForServerInit {
}
