/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;




/**
 * <P>JSmod2的主启动类，实例化了ServerStarter对象，并对于Windows的
 * ansi字符做了兼容支持(对于非rpc状态下)
 * <P>JSmod2-Core是JSmod2的体系全称,事实上JSmod2-Core分为JPLS(Java Plugin
 * Loading System)和JSmod2(Java Server Mod),JPLS为插件的驱动器,JSmod2则是
 * 插件的api,完整的可执行JSmod2称作JSmod2-Core,包含对外api和驱动部分,同时JSmod2-Core
 * 也可以分为Core和JSmod2-System,Core为封装好的框架(JSmod2开发组将共性封装到了cn.jsmod2.core包里)
 * JSmod2-System则是基于该框架实现的功能部分,事实上Core已经实现了大部分功能,System只是结合这些功能,定义了
 * 和游戏兼容的api
 * <P>JSmod2的启动配置只需要指定好main类所在的位置(JavaMultiModStarterAdmin)即可,其他配置文件会在JSmod2
 * 第一次启动时生成
 * <P>同时建议使用由Python开发的ui启动器和管理工具(start.py)来开启JSmod2(此时开启的为rpc心跳模式),这样更简单
 * 高效的管理JSmod2
 * <P>JSmod2不要远程和multiAdmin/LocalAdmin进行连接,必须放在同一系统内,否则网络波动的现象导致发生奇怪的问题,
 * 这里已经声明了这一点
 * <P>如果使得JSmod2生效,必须在MultiAdmin|LocalAdmin的sm_plugins文件夹里装载ProxyHandler.dll,使得JSmod2
 * 能够连接和找到游戏服务器,并提供插件的操作
 * <P>如果对该项目感兴趣,<a href="https://github.com/jsmod2-java-c/JSmod2-Core">加入我们</a>参与项目的开发
 * 或者提供一个star或者fork,感谢您的支持
 *
 * <P>JSmod2's main startup class, instantiated the ServerStarter object, and for Windows
 * Ansi characters do compatible support (for non-rpc state)
 * <P>JSmod2-Core is the full name of JSmod2 system. In fact, JSmod2-Core is divided into JPLS (Java Plugin).
 * Loading System) and JSmod2 (Java Server Mod), JPLS is the plugin driver, JSmod2 is
 * Plugin api, full executable JSmod2 called JSmod2-Core, contains external api and driver parts, and JSmod2-Core
 * Can also be divided into Core and JSmod2-System, Core is a packaged framework (JSmod2 development group will be common to the cn.jsmod2.core package)
 * JSmod2-System is based on the functional part of the framework implementation. In fact, Core has implemented most of the functions. System just combines these functions and defines
 * Game compatible api
 * <P>JSmod2 startup configuration only needs to specify the location of the main class (JavaMultiModStarterAdmin), other configuration files will be in JSmod2
 * Generated on first boot
 * <P>It is also recommended to use ui launcher and management tool (start.py) developed by Python to enable JSmod2 (the rpc heartbeat mode is enabled at this time), which is simpler.
 * Efficient management of JSmod2
 * <P>JSmod2 should not be connected remotely to multiAdmin/LocalAdmin and must be placed in the same system. Otherwise, network fluctuations may cause strange problems.
 * This has been stated here
 * <P>If JSmod2 is in effect, ProxyHandler.dll must be loaded in the sm_plugins folder of MultiAdmin|LocalAdmin to make JSmod2
 * Ability to connect and find game servers and provide plugin operations
 * <P>If you are interested in this project, <a href="https://github.com/jsmod2-java-c/JSmod2-Core">join us</a> to participate in the development of the project.
 * Or provide a star or fork, thank you for your support
 * @author magiclu550 #(code) jsmod2
 */
public class JavaMultiModStarterAdmin {

    /**
     * 主启动方法
     * @param args 启动参数和选项
     *             <P>
     *             -rpc 端口号，以RPC形式启动，一般用于启动器，如果有该选项，则
     *             其他选项不得存在.
     *             </P>
     *              -w -u -lr -lm -github -n -a
     *              -w 打开web
     *              -u 打开ui
     *              -lr 打开round的log监听
     *              -lm 打开multiAdmin和游戏的log监听
     *              -github 打开和Github连接
     *              -n 打开client处理
     *              -a 打开全部
     */
    public static void main(String[]args){
        ServerStarter.getInstance().start(args);
    }


}
