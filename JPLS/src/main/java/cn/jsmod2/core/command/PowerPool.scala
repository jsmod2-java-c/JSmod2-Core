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
      powers.get.add("cn.jsmod2.admin")
    }
  }

  def addAdmin(name:String): Unit ={
    OpsFile.getInstance.addOp(name)
  }

  def removeAdmin(name:String): Unit={
    OpsFile.getInstance.removeOp(name)
  }

  def addAdminMemory(name:String): Unit ={
    val find = poolMapping.get(name)
    if(find==null){
      throw new NoSuchPlayerException("no such cn.jsmod2.player name")
    }
    if(!find.get.contains("cn.jsmod2.admin"))
      find.get.add("cn.jsmod2.admin")
  }

  def removeAdminMemory(name:String): Boolean ={
    val find = poolMapping.get(name)
    if(find==null){
      throw new NoSuchPlayerException("no such cn.jsmod2.player name")
    }
    find.get.remove("cn.jsmod2.admin")
  }

}
