package cn.jsmod2;

import cn.jsmod2.core.Application;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.SpringContextUtil;
import cn.jsmod2.core.utils.Utils;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static cn.jsmod2.core.utils.Utils.contains;

/**
 * -w -u -lr -lm -github -a
 * -w 关闭web
 * -u 关闭ui
 * -lr 关闭round的log监听
 * -lm 关闭multiAdmin和游戏的log监听
 * -github 关闭和Github连接
 * -a 关闭全部
 */
@ServerApplication(DefaultServer.class)
public class ServerStarter {

    public void start(String[] args){
        if(contains(args,"-a")){
            args[0] = "-w-u-lr-l-github";
        }
        Utils.TryCatch(()->{
            CountDownLatch latch = new CountDownLatch(1);
            if(!contains(args,"-w")) {
                new Thread(() -> {
                    SpringContextUtil.setApplicationContext(Starter.run(args));
                    latch.countDown();
                }).start();
                latch.await();
            }
            if(!contains(args,"-u")) {
                CountDownLatch latch1 = new CountDownLatch(1);
                new Thread(() -> {
                    latch1.countDown();
                    try {
                        UIStarter.run(args);
                    } catch (Exception e) {
                        Utils.printException(e);
                    }
                }).start();
                latch1.await();
            }
            Application.run(this.getClass(),args);
        });
    }


}
