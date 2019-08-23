/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.player;

public enum  UserRank {
    ADMIN(5),
    PROJECT_MANAGER(4),
    GAME_STAFF(3),
    BETATESTER(2),
    PATREON_SUPPORTED(1),
    NONE(0);

    private int rank;

    public int getRank(){
        return rank;
    }

    UserRank(int rank){
        this.rank = rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
