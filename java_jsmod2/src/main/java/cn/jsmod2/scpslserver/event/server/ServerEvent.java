/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.Smod2Server;
import cn.jsmod2.scpslserver.event.Event;

public abstract class ServerEvent extends Event {

    private Smod2Server server;

    public ServerEvent(Smod2Server server){
        this.server = server;
    }

    public ServerEvent(){

    }

    public Smod2Server getServer() {
        return server;
    }

    /** java-bean */
    @UseForServerInit
    public void setServer(Smod2Server server) {
        this.server = server;
    }
}
