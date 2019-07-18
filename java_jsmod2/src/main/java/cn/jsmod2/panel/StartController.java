package cn.jsmod2.panel;

import cn.jsmod2.core.log.ServerLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@UIController
public class StartController {

    private AtomicBoolean consoleRun = new AtomicBoolean();

    private AtomicBoolean serverRun = new AtomicBoolean();

    private AtomicBoolean playersRun = new AtomicBoolean();

    private AtomicBoolean gameRun = new AtomicBoolean();

    @FXML
    private AnchorPane pane;


    @FXML
    private void onConsole(ActionEvent e){
        new Thread(()->{
            serverRun.set(false);
            playersRun.set(false);
            gameRun.set(false);
            consoleRun.set(true);
            try {
                double last = 0.0;
                while (consoleRun.get()) {
                    String msg = ServerLogger.getLogger().getQueue().take();
                    Text text = new Text();
                    List<Node> nodes = pane.getChildren();

                    if(last == 0.0) {
                        for (Node node : nodes) {
                            if (node instanceof ToolBar) {
                                ToolBar bar = (ToolBar) node;
                                last = bar.getLayoutX() + bar.getWidth() + 5.0;
                            }
                        }
                    }else{
                        last+=1.0;
                    }
                    text.setX(last);
                    text.setY(5.0);
                    text.setText(msg);
                    pane.getChildren().add(text);
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void onServer(ActionEvent e){
        serverRun.set(true);
        playersRun.set(false);
        gameRun.set(false);
        consoleRun.set(false);
    }

    @FXML
    private void onPlayers(ActionEvent e){
        serverRun.set(false);
        playersRun.set(true);
        gameRun.set(false);
        consoleRun.set(false);
    }

    @FXML
    private void onGame(ActionEvent e){
        serverRun.set(false);
        playersRun.set(false);
        gameRun.set(true);
        consoleRun.set(false);
    }


}
