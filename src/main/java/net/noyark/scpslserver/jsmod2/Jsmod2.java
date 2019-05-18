//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG
//          佛曰:
//              写字楼里写字间，写字间里程序员；
//              程序人员写程序，又拿程序换酒钱。
//              酒醒只在网上坐，酒醉还来网下眠；
//              酒醉酒醒日复日，网上网下年复年。
//              但愿老死电脑间，不愿鞠躬老板前；
//              奔驰宝马贵者趣，公交自行程序员。
//              别人笑我忒疯癫，我笑自己命太贱；
//              不见满街漂亮妹，哪个归得程序员？
package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.impl.log.ServerLogger;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author magiclu550 #(code) jsmod2
 */


public class Jsmod2 {

    public static final String START = "start";


    static {
        Register.getInstance().registerStartInfo();
        Register.getInstance().registerSuccessInfo();
    }

    private static ILogger log = new ServerLogger();

    //
    // 选择语言部分->正常启动
    //

    public static void main(String[]args){
        try{
            //lang properties
            Properties langProperties = FileSystem.getFileSystem().langProperties(log);
            long start = System.currentTimeMillis();
            startMessage(langProperties);
            new Server(log,langProperties);
            long startSuccess = System.currentTimeMillis();
            for(String success:Register.getInstance().getSuccessInfo()){
                log.info(MessageFormat.format(langProperties.getProperty(success),(startSuccess-start)+""));
            }
            Console.getConsole().commandInput();
        }catch (Exception e){
            log.error("this is a exception!!!");
            e.printStackTrace();
        }
    }





    public static void startMessage(Properties langProperties){
        //plugin dir
        for(String info:Register.getInstance().getStartInfo()){
            log.info(langProperties.getProperty(info));
        }
    }
}
