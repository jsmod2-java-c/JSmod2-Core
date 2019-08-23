/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.annotations;

import cn.jsmod2.core.utils.EventPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于在监听器使用，注解在方法上，当注册监听器时，
 * 标记该注解的方法会被收集，并根据监听器类型调用方法.
 *
 * <code>
 * @ EventManager
 * public void onTest(ITestEvent e){
 *     //...
 * }
 * </code>
 * @author magiclu550 #(code) jsmod2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventManager {

    EventPriority priority() default EventPriority.NORMAL;

}
