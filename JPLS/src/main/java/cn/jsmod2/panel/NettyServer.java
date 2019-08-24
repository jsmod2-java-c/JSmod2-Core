package cn.jsmod2.panel;


import cn.jsmod2.Register;
import cn.jsmod2.core.Console;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.utils.Utils;
import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.HashMap;
import java.util.Map;


class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String value = (String) msg;
        Map<String,String> map = JSON.parseObject(value, HashMap.class);
        String type = map.get("type");
        Handler handler = NettyServer.getHandlers().get(type);
        Response response = handler.handle(map);
        if(response != null){
            ctx.writeAndFlush(JSON.toJSONString(new HashMap<String,String>(){
                {
                    put("value",response.value);
                    put("message",response.message);
                    put("status",response.status);
                }
            }));
        }else{
            ctx.writeAndFlush(JSON.toJSONString(new HashMap<String,String>(){
                {
                    put("value","null");
                    put("message","null");
                    put("status","201");
                }
            }));
        }
        ctx.flush();
        // 必须要调用Flush方法才能放到通道里去发送。
    }
}

class Response{
    public String value;
    public String message;
    public String status;

    public Response(String value, String message, String status) {
        this.value = value;
        this.message = message;
        this.status = status;
    }
}

interface Handler{
    Response handle(Map<String,String> map);
}

class LogHandler implements Handler{
    @Override
    public Response handle(Map<String, String> map) {
        String str = ServerLogger.getLogger().getQueue().peek();
        if(str == null){
            return new Response("null","null","201");
        }
        return new Response(str,str,"200");
    }
}

class CommandHandler implements Handler{
    @Override
    public Response handle(Map<String, String> map) {
        String command = map.get("command");
        Console.getConsole().runConsoleCommandWithEmerald(command);
        return null;
    }
}

public class NettyServer implements Runnable{

    private static Map<String,Handler> handlers = new HashMap<>();

    static {
        handlers.put("log",new LogHandler());
        handlers.put("command",new CommandHandler());
    }

    public static Map<String, Handler> getHandlers() {
        return handlers;
    }

    public void run() {
        try {
            ServerLogger.getLogger().multiInfo(getClass(),"Starting control server...","","");
            // 1.创建两个线程池，一个负责接受客户端，一个进行传输
            NioEventLoopGroup pGroup = new NioEventLoopGroup();
            NioEventLoopGroup cGroup = new NioEventLoopGroup();
            // 2.创建辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(pGroup, cGroup).channel(NioServerSocketChannel.class)
                    // 3.设置缓冲区大小
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 4.设置发送缓冲区大小
                    .option(ChannelOption.SO_SNDBUF, 1024)
                    // 5.配置handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 让返回的是字符串类型 设置返回的结果是String类型
                            sc.pipeline().addLast(new StringDecoder());
                            sc.pipeline().addLast(new ServerHandler());
                        }
                    });
            // 启动netty
            ChannelFuture cFuture = serverBootstrap.bind(Integer.parseInt(Server.getSender().getServer().serverProp.getProperty(Register.CLIENT_PORT,"20020"))).sync();
            // 关闭
            cFuture.channel().closeFuture().sync();
            pGroup.shutdownGracefully();
            cGroup.shutdownGracefully();
        } catch (Exception e) {
            Utils.printException(e);
        }

    }
}