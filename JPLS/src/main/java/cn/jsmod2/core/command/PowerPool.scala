/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.core.command

import java.util

import cn.jsmod2.core.Server
import cn.jsmod2.core.ex.NoSuchPlayerException

import scala.collection.mutable

/**
 * 权限池，用于权限检测和指令的权限拉取
 * 领取地点 数据包管理段
  *
  * @author magiclu550
 */
object PowerPool {

  //名字-权限
  final var poolMapping = new mutable.HashMap[String,util.List[String]]()

  private val ops = OpsFile.getInstance().getOps

  private val players = Server.getSender.getServer.getGameServer.getPlayers

  //把全部权限加载进去
  for(p <- 0 until players.size){
    val player = players.get(p)
    poolMapping.put(player.getName,player.getPowers)
  }

  /**
    * 把全部op信息加载进去
    */
  for(i <- 0 until ops.size()){
    val powers = poolMapping.get(ops.get(i))
    if(powers != null){
      powers.get.add("admin")
    }
  }

  def addAdmin(name:String): Unit ={
    addAdminMemory(name)
    OpsFile.getInstance.addOp(name)
  }

  def removeAdmin(name:String): Unit={
    removeAdminMemory(name)
    OpsFile.getInstance.removeOp(name)
  }

  def addAdminMemory(name:String): Unit ={
    val find = poolMapping.get(name)
    if(find==null){
      throw new NoSuchPlayerException("no such player name")
    }
    if(!find.get.contains("admin"))
      find.get.add("admin")
  }

  def removeAdminMemory(name:String): Boolean ={
    val find = poolMapping.get(name)
    if(find==null){
      throw new NoSuchPlayerException("no such player name")
    }
    find.get.remove("admin")
  }

}
