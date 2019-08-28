/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;


import org.fusesource.jansi.AnsiConsole;

/**
 * Jsmod2的主启动类，实例化了ServerStarter对象，并对于Windows的
 * ansi字符做了兼容支持
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
        AnsiConsole.systemInstall();
        ServerStarter starter = new ServerStarter();
        starter.start(args);
    }


}
