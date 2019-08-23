/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.team;


import cn.jsmod2.core.interapi.event.IEvent;

public interface ISetSCPConfigEvent extends IEvent {

    boolean isBan049();

    void setBan049(boolean ban049);

    boolean isBan079();

    void setBan079(boolean ban079);

    boolean isBan096();

    void setBan096(boolean ban096);

    boolean isBan106();

    void setBan106(boolean ban106);

    boolean isBan173();

    void setBan173(boolean ban173);

    boolean isBan939_53();

    void setBan939_53(boolean ban939_53);

    boolean isBan939_89();

    void setBan939_89(boolean ban939_89);

    int getSCP049amount();

    void setSCP049amount(int SCP049amount);

    int getSCP079amount();

    void setSCP079amount(int SCP079amount);

    int getSCP096amount();

    void setSCP096amount(int SCP096amount);

    int getSCP106amount();

    void setSCP106amount(int SCP106amount);

    int getSCP173amount();

    void setSCP173amount(int SCP173amount);

    int getSCP457amount();

    void setSCP457amount(int SCP457amount);

    int getSCP939_53amount();

    void setSCP939_53amount(int SCP939_53amount);

    int getSCP939_89amount();

    void setSCP939_89amount(int SCP939_89amount);
}
