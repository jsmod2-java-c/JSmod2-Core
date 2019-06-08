package cn.jsmod2.command

import java.util

import cn.jsmod2.ex.NoSuchPlayerException
import cn.jsmod2.{OpsFile, Server}

import scala.collection.mutable

/**
 * 权限池，用于权限检测和指令的权限拉取
 * 领取地点 数据包管理段
  *
  * @author magiclu550
 */
object PowerPool {

  final var poolMapping = new mutable.HashMap[String,util.List[String]]()

  var ops = OpsFile.getOpsFile.getOps

  var players = Server.getSender.getServer.getSmod2Server.getPlayers

  //把全部权限加载进去
  for(p <- 0 until players.size){
    val player = players.get(p)
    poolMapping.put(player.getName,player.getPowers)
  }

  /**
    * 把全部op信息加载进去
    */
  for(i <- 0 until ops.size()){
    val powers = poolMapping.get(ops(i))
    if(powers != null){
      powers.get.add("admin")
    }
  }

  def addAdmin(name:String): Unit ={
    OpsFile.getOpsFile.addOp(name)
  }

  def removeAdmin(name:String): Unit={
    OpsFile.getOpsFile.removeOp(name)
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
