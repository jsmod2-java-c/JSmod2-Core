/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.DamageType;
import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.player.WeaponType;
import cn.jsmod2.core.math.Vector;

/**
 * @author kevinj
 */
public interface IPlayerShootEvent extends IPlayerEvent {

    boolean isShouldSpawnHitmarker();

    void setShouldSpawnHitmarker(boolean shouldSpawnHitmarker);

    boolean getShouldSpawnBloodDecal();

    void setShouldSpawnBloodDecal(boolean shouldSpawnBloodDecal);

    Vector getDirection();

    void setDirection(Vector direction);

    IPlayer getTarget();

    DamageType getWeapon();

    Vector getSourcePosition();

    Vector getTargetPosition();

    String getTargetHitbox();


    WeaponType getWeaponSound();

    void setWeaponSound(WeaponType weaponSound);

}
