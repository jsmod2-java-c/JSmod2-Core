package cn.jsmod2.starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class JSmod2Starter extends Application {

    public static void main(String[] args){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        primaryStage.setTitle("Jsmod2-Starter");
        primaryStage.setResizable(false);
        Image image= new Image(this.getClass().getResource("favicon.gif").toString(), 100, 150, false, false);
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
}
