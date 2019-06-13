/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.command.*;
import cn.jsmod2.event.admin.AdminQueryEvent;
import cn.jsmod2.event.admin.AuthCheckEvent;
import cn.jsmod2.event.admin.BanEvent;
import cn.jsmod2.event.config.SetConfigEvent;
import cn.jsmod2.event.environment.*;
import cn.jsmod2.event.player.*;
import cn.jsmod2.event.server.*;
import cn.jsmod2.event.team.DecideRespawnQueueEvent;
import cn.jsmod2.event.team.SetSCPConfigEvent;
import cn.jsmod2.ex.*;
import cn.jsmod2.network.ServerInitPacket;
import cn.jsmod2.network.DataPacket;
import cn.jsmod2.event.Event;
import cn.jsmod2.event.team.SetNTFUnitNameEvent;
import cn.jsmod2.event.team.TeamRespawnEvent;
import cn.jsmod2.network.command.CommandRegisterPacket;
import cn.jsmod2.network.command.PlayerCommandPacket;
import cn.jsmod2.network.command.ServerCommandPacket;
import scala.reflect.io.File;

import java.util.*;

import static cn.jsmod2.Jsmod2.START;

/**
 * 所有注册的中心类
 * @author magiclu550
 */

public class Register {


    public static final int MAX_EVENT_ID = 0x52;

    public static final int FRIST_EVENT = 0x01;

    public static final int SECOND_START_EVENT = 0x03;

    public static final int SERVER_COMMAND = 0x55;

    public static final int PLAYER_COMMAND = 0x56;

    /**
     * 注册语言时，首先按照标准格式添加语言
     * 参考resources格式
     */

    public void registerLang(){
        registerLang.add("zh");
        registerLang.add("en");
        registerLang.add("ru");
        registerLang.add("jp");
        registerLang.add("ar");
        registerLang.add("sp");
    }


    public void registerNativeCommand(){
        nativeCommandMap.put("stop",new StopCommand());
        nativeCommandMap.put("help",new HelpCommand());
        nativeCommandMap.put("plugins",new PluginsCommand());
        nativeCommandMap.put("reload",new ReloadCommand());
        nativeCommandMap.put("tab",new TabCommand());
        nativeCommandMap.put("see",new SeeCommand());
    }

    public void registerStartInfo(){
        startInfo.add(START+".info");
        startInfo.add(START+".thanks");
        startInfo.add(START+".warn");
        startInfo.add(START+".connect");
    }

    public void registerPacket(){
        packets.put(ServerInitPacket.class,0x00);
        packets.put(CommandRegisterPacket.class,0x53);
        packets.put(ServerCommandPacket.class,Register.SERVER_COMMAND);
        packets.put(PlayerCommandPacket.class,Register.PLAYER_COMMAND);
        putPackets();
    }


    public void putPackets(){
        Set<Map.Entry<Class<? extends DataPacket>,Integer>> dataPackets = packets.entrySet();
        for(Map.Entry<Class<? extends DataPacket>,Integer> packet:dataPackets){
            getPackets.put(packet.getValue(),packet.getKey());
        }
    }

    public void registerSuccessInfo(){
        successInfo.add("start.finish");
    }

    public void registerServerProperties(){
        serverProperties.put(FileSystem.PLUGIN_PORT,"19938");//插件端port
        //serverProperties.put(FileSystem.SERVER_INIT_PORT,"19939");//服务端初始化的端口，传输server信息
        serverProperties.put(FileSystem.SERVER_DECODE,"utf-8");//解码字符集
        serverProperties.put(FileSystem.SERVER_ENCODE,"utf-8");//编码字符集
        serverProperties.put(FileSystem.THIS_PORT,"19935");//本服务端的port
        serverProperties.put(FileSystem.SMOD2_IP,"127.0.0.1");
    }

    public void registerException(){
        ex_methods.put (TypeErrorException.class,"* your configuration file type may have some problems, please see your configuration file type*\n\t refer to cn.jsmod2.utils.config.ConfigQueryer class\n\t or cn.jsmod2.configs.ConfigType class");
        ex_methods.put (ProtocolException.class,"* when transferring protocol, you did not transfer data in accordance with an accurate or correct jsmod2 * \n \t reference grammar ID - {main json}, field chain: {mapping JSON value} ~ tail request (optional, to mark subsidiary information, or ownership information");
        ex_methods.put (PluginException.class, "*There is a serious problem in initializing plug-in objects. Please check that the main class of your configuration file is filled in correctly*");
        ex_methods.put (NoSuchPluginNameException. class, "* when using the PluginManager. getPlugin method, there is no plug-in name caused by *");
        ex_methods.put (NoSuchPlayerException. class, "* can't find this object *when the PowerPool class of administrative permissions has problems, deletes or adds permissions");
        ex_methods.put (MainClassErrorException. class, "* main class name error, if there is no such class *");
        ex_methods.put (EventException. class, "* caused by a problem with the name of the event method parameter, or there is no event *");
    }

    /**
     * 格式:
     * put(事件id,事件名.class)
     * 事件id对应数据包id
     */
    public void registerEvents(){
        events.put(0x01, AdminQueryEvent.class);//packet
        events.put(0x03, AuthCheckEvent.class);//packet
        events.put(0x04, BanEvent.class);//packet
        events.put(0x05, SetConfigEvent.class);//packet
        events.put(0x06, GeneratorFinishEvent.class);//p
        events.put(0x07, LCZDecontaminateEvent.class);//p
        events.put(0x08, SCP914ActivateEvent.class);//p
        events.put(0x09, ScpDeathAnnouncementEvent.class);//p
        events.put(0x0a, SummonVehicleEvent.class);//p
        events.put(0x0b,WarheadChangeLeverEvent.class);//p
        events.put(0x0c,WarheadDetonateEvent.class);//p
        events.put(0x0d,WarheadKeycardAccessEvent.class);//p
        events.put(0x0e,WarheadStartEvent.class);//p
        events.put(0x0f, Player079AddExpEvent.class);//p
        events.put(0x10, Player079CameraTeleportEvent.class);//p
        events.put(0x11, Player079DoorEvent.class);
        events.put(0x12, Player079ElevatorTeleportEvent.class);
        events.put(0x13, Player079LevelUpEvent.class);
        events.put(0x14,Player079LockdownEvent.class);
        events.put(0x15,Player079LockEvent.class);
        events.put(0x16,Player079StartSpeakerEvent.class);
        events.put(0x17,Player079StopSpeakerEvent.class);
        events.put(0x18,Player079TeslaGateEvent.class);
        events.put(0x19,Player079UnlockDoorsEvent.class);
        events.put(0x1a,Player106CreatePortalEvent.class);
        events.put(0x1b,Player106TeleportEvent.class);
        events.put(0x1c,PlayerCallCommandEvent.class);
        events.put(0x1d,PlayerCheckEscapeEvent.class);
        events.put(0x1e,PlayerContain106Event.class);
        events.put(0x1f,PlayerDeathEvent.class);
        events.put(0x20,PlayerDropItemEvent.class);
        events.put(0x21,PlayerElevatorUseEvent.class);
        events.put(0x22,PlayerGeneratorAccessEvent.class);
        events.put(0x23,PlayerGeneratorEjectTabletEvent.class);
        events.put(0x24,PlayerGeneratorInsertTabletEvent.class);
        events.put(0x25,PlayerGeneratorUnlockEvent.class);
        events.put(0x26,PlayerGrenadeExplosion.class);
        events.put(0x27,PlayerGrenadeHitPlayer.class);
        events.put(0x28,PlayerHandcuffedEvent.class);
        events.put(0x29,PlayerHurtEvent.class);
        events.put(0x2a,PlayerInitialAssignTeamEvent.class);
        events.put(0x2b,PlayerIntercomCooldownCheckEvent.class);
        events.put(0x2c,PlayerIntercomEvent.class);
        events.put(0x2d,PlayerJoinEvent.class);
        events.put(0x2e,PlayerLureEvent.class);
        events.put(0x2f,PlayerMakeNoiseEvent.class);
        events.put(0x30,PlayerMedkitUseEvent.class);
        events.put(0x31,PlayerPickupItemEvent.class);
        events.put(0x32,PlayerPickupItemLateEvent.class);
        events.put(0x33,PlayerPocketDimensionEnterEvent.class);
        events.put(0x34,PlayerPocketDimensionExitEvent.class);
        events.put(0x35,PlayerRadioSwitchEvent.class);
        events.put(0x36,PlayerRecallZombieEvent.class);
        events.put(0x37,PlayerReloadEvent.class);
        events.put(0x38,PlayerSCP914ChangeKnobEvent.class);
        events.put(0x39,PlayerSetRoleEvent.class);
        events.put(0x3a,PlayerShootEvent.class);
        events.put(0x3b,PlayerSpawnEvent.class);
        events.put(0x3c,PlayerSpawnRagdollEvent.class);
        events.put(0x3d,PlayerThrowGrenade.class);
        events.put(0x3e,PlayerTriggerTeslaEvent.class);
        events.put(0x3f,Scp096CooldownEndEvent.class);
        events.put(0x40,Scp096CooldownStartEvent.class);
        events.put(0x41,Scp096EnrageEvent.class);
        events.put(0x42,Scp096PanicEvent.class);
        events.put(0x43, ConnectEvent.class);
        events.put(0x44, DisconnectEvent.class);
        events.put(0x45, FixedEvent.class);
        events.put(0x46, LateDisconnectionEvent.class);
        events.put(0x47, LateUpEvent.class);
        events.put(0x48,RoundEndEvent.class);
        events.put(0x49,RoundReStartEvent.class);
        events.put(0x4a,RoundStartEvent.class);
        events.put(0x4b,SceneChangedEvent.class);
        events.put(0x4c,SetServerNameEvent.class);
        events.put(0x4d,UpdateEvent.class);
        events.put(0x4e,WaitingForPlayersEvent.class);
        events.put(0x4f, DecideRespawnQueueEvent.class);
        events.put(0x50, SetNTFUnitNameEvent.class);
        events.put(0x51, SetSCPConfigEvent.class);
        events.put(0x52, TeamRespawnEvent.class);
        //82 events
    }

    public List<String> getRegisterLang(){
        return registerLang;
    }

    public Map<String, NativeCommand> getNativeCommandMap(){
        return nativeCommandMap;
    }

    public List<String> getStartInfo() {
        return startInfo;
    }


    public Map<Class<? extends Exception>, String> getEx_methods() {
        return ex_methods;
    }

    public List<String> getSuccessInfo() {
        return successInfo;
    }

    public Map<Class<? extends DataPacket>,Integer> getPackets(){
        return packets;
    }

    public Map<Integer, Class<? extends Event>> getEvents() {
        return events;
    }

    public static Register getInstance(){
        return register;
    }

    public Map<String, String> getServerProperties() {
        return serverProperties;
    }

    private static Register register;

    private static List<String> registerLang = new ArrayList<>();

    private Map<String, NativeCommand> nativeCommandMap = new HashMap<>();

    private List<String> startInfo = new ArrayList<>();

    private List<String> successInfo = new ArrayList<>();

    private Map<Class<? extends DataPacket>,Integer> packets = new HashMap<>();

    private Map<String,String> serverProperties = new HashMap<>();

    private Map<Integer, Class<? extends Event>> events = new HashMap<>();

    private Map<Integer,Class<? extends DataPacket>> getPackets = new HashMap<>();

    private Map<Class<? extends Exception>,String> ex_methods = new HashMap<>();

    public Map<Integer, Class<? extends DataPacket>> getGetPackets() {
        return getPackets;
    }

    public void setGetPackets(Map<Integer, Class<? extends DataPacket>> getPackets) {
        this.getPackets = getPackets;
    }


    //注册
    static {
        register = new Register();
        getInstance().registerEvents();
        getInstance().registerException();
        getInstance().registerLang();
        getInstance().registerNativeCommand();
        getInstance().registerPacket();
        getInstance().registerServerProperties();
        getInstance().registerStartInfo();
        getInstance().registerSuccessInfo();
    }
}
