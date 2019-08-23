package cn.jsmod2.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于继承RegisterTemplate类使用，在注册RegisterTemplate子类时，
 * 会调用标注该注解的方法，完成服务器属性的注册
 * @author magiclu550
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterMethod {
}
