package cn.jsmod2.scpslserver.event;

import cn.jsmod2.scpslserver.utils.EventPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author magiclu550 #(code) jsmod2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventManager {

    EventPriority priority() default EventPriority.NORMAL;

}
