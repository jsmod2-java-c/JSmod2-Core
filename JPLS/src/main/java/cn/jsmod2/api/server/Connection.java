/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.server;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.network.DoStream;
import cn.jsmod2.network.SimpleGetStream;

import java.io.Serializable;

public class Connection extends ApiId implements IConnection, Serializable,Cloneable {

    private String ipAddress;

    private boolean isBanned;

    public void disconnect(){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Disconnect";
        stream.send();
    }


    public String getIpAddress() {
        SimpleGetStream stream = new SimpleGetStream(String.class);
        ipAddress = stream.read(playerName,"IpAddress",String.class);
        return ipAddress;
    }

    public boolean isBanned() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        isBanned = stream.read(playerName,"IsBanned",Boolean.class);
        return isBanned;
    }


}
