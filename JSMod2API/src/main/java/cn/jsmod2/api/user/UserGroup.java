/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.user;

import cn.jsmod2.core.ApiId;

import java.io.Serializable;

public class UserGroup extends ApiId implements IUserGroup, Serializable,Cloneable {

    private String color;

    private String badgeText;

    private long permissions;

    private boolean cover;

    private boolean hiddenByDefault;

    private String name;

    //这里未来解决
    public Object getComponent(){
        return null;
    }

    public String getColor() {
        return color;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public long getPermissions() {
        return permissions;
    }

    public boolean isCover() {
        return cover;
    }

    public boolean isHiddenByDefault() {
        return hiddenByDefault;
    }

    public String getName() {
        return name;
    }
}
