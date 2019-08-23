/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.utils;

import java.lang.reflect.Method;


/**
 * 存储优先级和方法对象的映射
 *
 * @author magiclu550
 */
public class MethodInvokeMapper {

    private EventPriority priority;

    private Method method;

    public MethodInvokeMapper(EventPriority priority,Method method){
        this.priority = priority;
        this.method = method;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }
}
