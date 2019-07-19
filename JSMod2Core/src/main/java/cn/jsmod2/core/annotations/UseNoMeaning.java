package cn.jsmod2.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明该方法在外部调用是没有意义的，用于特殊调用
 * @author magiclu550 #(code) jsmod2
 */

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseNoMeaning {
}
