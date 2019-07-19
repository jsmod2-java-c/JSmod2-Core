package cn.jsmod2.core.forplayer;

import cn.jsmod2.core.annotations.Param;
import cn.jsmod2.core.annotations.Type;
import cn.jsmod2.core.math.Vector;

/**
 * RegisterType的作用是允许在一个玩家发出请求，并将修改通知全部玩家之前，做中间的切面处理
 */
public class RegisterType {
    //entity的entries里面存着全部的json信息，可以通过它来重组对象
    //并通过entity发出去
    @Type("add.health")
    public void healthAddHandler(PlayerEntity entity,@Param("health")Integer health){
        entity.addHealth(health);
    }

    @Type("sub.health")
    public void healthSubHandler(PlayerEntity entity,@Param("health")Integer health){
        entity.subHealth(health);
    }

    @Type("move")
    public void moveHandler(PlayerEntity entity,@Param("x")Double x,@Param("y")Double y,@Param("z")Double z){
        Vector vector = new Vector(x,y,z);
        entity.move(vector);
    }
    //这里是自定义设置玩家的名字
    //这里一个玩家发送了一个json请求
    //这里简化写
    //{type:example,name:xxx,steamId:1,makeId:2,skill:aa}
    //这里setSkill调用了send，这个处理后的skill，将会通知到全部目前在线的玩家
    //全部玩家看到的这个玩家对象会发生改变
    @Type("example")
    public void exampleHandler(
            PlayerEntity entity
            ,@Param("steamId")Integer id
            ,@Param("makeId")Integer makeId
            ,@Param("skill")String skill
    ){
        ExamplePlayer player = new ExamplePlayer(id,makeId,skill,entity);
        player.setSkill(skill);//这里发送了send
    }
}
