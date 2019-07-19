/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.server;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.annotations.UseForServerInit;

public class Connection extends ApiId {

    private String ipAddress;

    private boolean isBanned;

    public void disconnect(){

    }


    public String getIpAddress() {
        return ipAddress;
    }
    @UseForServerInit
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isBanned() {
        return isBanned;
    }
    @UseForServerInit
    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
