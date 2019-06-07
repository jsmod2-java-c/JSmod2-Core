/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.utils.player;

public enum RadioStatus {

    CLOSE(0),
    SHORT_RANGE(1),
    MEDIUM_RANGE(2),
    LONG_RANGE(3),
    ULTRA_RANGE(4);

    private int status;

    public int getStatus(){
        return status;
    }

    RadioStatus(int status){
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
