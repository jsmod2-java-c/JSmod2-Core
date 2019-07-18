/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.core.Application;
import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.panel.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * @author magiclu550 #(code) jsmod2
 */

@ServerApplication(DefaultServer.class)
@SpringBootApplication
@RegisterController("cn.jsmod2.ui")
public class Jsmod2 extends javafx.application.Application {

    public static void main(String[]args){
        Utils.TryCatch(()->{
            CountDownLatch latch = new CountDownLatch(1);
            new Thread(()->{
                SpringApplication.run(Jsmod2.class,args);
                latch.countDown();
            }).start();
            latch.await();
            CountDownLatch latch1 = new CountDownLatch(1);
            new Thread(()->{
                latch1.countDown();
                Application.run(Jsmod2.class,args);
            }).start();
            latch1.await();
            launch(args);
            System.exit(0);
        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/start.fxml"));
        primaryStage.setTitle("Jsmod2-Control-Panel");
        primaryStage.setScene(new Scene(root, 625, 500));
        primaryStage.show();
    }
}
