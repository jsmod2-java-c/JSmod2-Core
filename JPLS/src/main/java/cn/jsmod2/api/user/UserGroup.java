/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.user;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.network.SimpleGetStream;

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
        SimpleGetStream stream = new SimpleGetStream(String.class);
        color = stream.read(playerName,"Color",String.class);
        return color;
    }

    public String getBadgeText() {
        SimpleGetStream stream = new SimpleGetStream(String.class);
        badgeText = stream.read(playerName,"BadgeText",String.class);
        return badgeText;
    }

    public long getPermissions() {
        SimpleGetStream stream = new SimpleGetStream(Long.class);
        permissions = stream.read(playerName,"Permissions",Long.class);
        return permissions;
    }

    public boolean isCover() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        cover = stream.read(playerName,"Cover",Boolean.class);
        return cover;
    }

    public boolean isHiddenByDefault() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        hiddenByDefault = stream.read(playerName,"HiddenByDefault",Boolean.class);
        return hiddenByDefault;
    }

    public String getName() {
        SimpleGetStream stream = new SimpleGetStream(String.class);
        name = stream.read(playerName,"Name",String.class);
        return name;
    }
}
