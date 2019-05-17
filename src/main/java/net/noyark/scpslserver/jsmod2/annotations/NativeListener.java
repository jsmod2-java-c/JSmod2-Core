package net.noyark.scpslserver.jsmod2.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于描述一个服务器自带的监听器,在清除时永远不会被清除，除非关闭服务器被销毁
 *
 * @author magiclu550
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NativeListener {
}
