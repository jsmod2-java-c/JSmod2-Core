/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.event;

import cn.jsmod2.core.ApiId;

import java.io.Serializable;

/**
 * 所有事件需要继承该类，在注册时，才能嵌入注册器，完成事件的注册
 * 在使用监听器时，不建议直接使用基类
 * @author magiclu550
 */

public abstract class Event extends ApiId implements Serializable,Cloneable {


    public final String getEventName() {
        return this.getClass().getName();
    }


}
