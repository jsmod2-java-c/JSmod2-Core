package cn.jsmod2;

import cn.jsmod2.core.Application;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.SpringContextUtil;
import cn.jsmod2.core.utils.Utils;

import java.util.concurrent.CountDownLatch;

@ServerApplication(DefaultServer.class)
public class ServerStarter {

    public void start(String[] args){
        Utils.TryCatch(()->{
            CountDownLatch latch = new CountDownLatch(1);
            new Thread(()->{
                SpringContextUtil.setApplicationContext(Starter.run(args));
                latch.countDown();
            }).start();
            latch.await();
            CountDownLatch latch1 = new CountDownLatch(1);
            new Thread(()->{
                latch1.countDown();
                try {
                    UIStarter.run(args);
                }catch (Exception e){
                    Utils.printException(e);
                }
            }).start();
            latch1.await();
            Application.run(this.getClass(),args);
        });
    }
}
