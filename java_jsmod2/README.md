# jsmod2.协议第三版，加入请求尾
packetId-mainJson,name:json,name:json...~request
头请求可有可无，特定数据包会标记
为了描述这个对象的归属
如a物品归属于b玩家
0x95-{物品对象的json串},附加特殊字段的json和字段链名~b
未来有时间会发一套完整的jsmod2协议库
之后写一下处理尾请求的部分
c#和java都写
添加个识别~的部分
请求有两种
c#发到java的整请求，把整个对象发过来，一般没有尾请求
事件，服务器初始化对象，关闭指令
触发指令发出指令请求(已经完成)
{
未来对指令进行更多的细化，如权限池
指令发来时，判断CommandSender，获取名，通过权限池映射到权限，然后和指令的权限比较，权限不足则返回需要xx权限，权限可以则执行

而commandSender定义的是默认权限
给予管理员后，会保存在ops.txt,重新启动服务器时，读取到权限池，在取消管理员时，先删除掉ops文件的名字，再在权限池内存删掉权限
}
java发到c#的修改请求，把修改值发过来，一般有尾请求
实体类修改发出的请求
c#
编码规则
id-主json,附加字段~尾请求
解码规则
拆分出id和尾请求，将json直接转化为对象

java
编码
id-将对象转化为json~尾请求
解码
拆分请求，转换主对象，并字段注入
其中拆分尾请求


# 修改请求的设计

对于Item的name进行修改

设计SetPacket类,SetPacket里面定义了修改数据的数据包

c#端设计同样的包

如对name修改的SetPacket类

ItemSetPacket extends DataPacket{
 String name
 String playerName
 encode()
 decode()
}

然后写一套标准协议库

public byte[] itemNameSet(String name,String playerName)

将数据转化为标准修改请求，放在encode作为返回值

# 对于权限的修改
一般当基于玩家管理员权限，发出请求，将会在请求池里进行修改
