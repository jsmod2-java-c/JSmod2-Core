/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
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
