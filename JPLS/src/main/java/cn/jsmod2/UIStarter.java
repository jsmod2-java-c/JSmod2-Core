package cn.jsmod2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
