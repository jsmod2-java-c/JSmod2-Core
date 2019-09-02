package cn.jsmod2.network;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.protocol.*;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;
import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

public class PacketSender {

    public static <T> T sendGetPacket(GetPacket p,Class<T> type){
        return Server.getRuntime().running().sendGetPacket(p,type);
    }

    public static void sendSetPacket(SetPacket packet){
        Server.getRuntime().running().sendSetPacket(packet);
    }

    public static <T> T sendEventGetPacket(String playerName,String key,Class<T> type){
        return sendEventGetPacket(playerName,key,type,type,GetTypes.GET);
    }

    public static <F> F sendEventGetPacket(String playerName, String key, Class<?> type,Class<F> returnType, int getTypes){
        EventValueGetStream stream = new EventValueGetStream(type);
        stream.playerName = playerName;
        stream.name = key;
        stream.getType = getTypes;
        return sendGetPacket(stream,returnType);
    }


    public static void sendEventSetPacket(String playerName,String key,Object value){
        EventValueSetStream packet = new EventValueSetStream();
        packet.playerName = playerName;
        packet.name = key;
        packet.value = value;
        sendSetPacket(packet);
    }

    public static Object getResponseValue(Response response,int getType){
        if(getType == GetTypes.GET)return response.get();
        if(getType == GetTypes.GET_ARRAY)return response.getArray();
        if(getType == GetTypes.GET_PROTOCOL_ARRAY_WITH_LIST_IN)return response.getProtocolArray(true);
        if(getType == GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN)return response.getProtocolArray(false);
        return response.get();
    }

    public static void req(Requester requester,String method,String[] args){
        requester.with("do",method);
        if(args != null){
            requester.with("args", Utils.arraysToString(args));
        }
    }

}
