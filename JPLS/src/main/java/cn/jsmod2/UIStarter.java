/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 老版本ui的启动器，目前不建议去使用
 * @author magiclu550
 */
public class UIStarter extends javafx.application.Application  {


    public static void run(String []args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/start.fxml"));
        primaryStage.setTitle("Jsmod2-Control-Panel");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
}
}
