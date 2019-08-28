package cn.jsmod2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解为了标记参与服务器测试的方法
 * <code>
 * @ ServerTest
 * @ Test
 * public void test(){
 *     //...
 * }
 * </code>
 * @author magiclu550
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ServerTest {
}
