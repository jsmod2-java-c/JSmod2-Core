package cn.jsmod2.panel;

import cn.jsmod2.core.log.ServerLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author GNX-Susanoo
 */
@UIController
public class StartController implements IUiController {
    private ServerLogger serverLogger = ServerLogger.getLogger();
    private volatile boolean consoleRun = false;
    private volatile boolean serverRun = false;

    private volatile boolean playersRun = false;

    private volatile boolean gameRun = false;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextArea consoleTextArea;
    @FXML
    public TextField lineHeightTextField;
    @FXML
    public CheckBox scollCheckBox;
    @FXML
    public Button lineHeightSettingButton;

    @Override
    public void init() {
        serverLogger.getConsoleOutputStream().setTextArea(consoleTextArea);
    }

    @FXML
    private void onConsole(ActionEvent e) {
        new Thread(() -> {
            serverRun = false;
            playersRun = false;
            gameRun = false;
            consoleRun = true;
            try {
                double last = 0.0;
                while (consoleRun) {
                    String msg = ServerLogger.getLogger().getQueue().take();
                    Text text = new Text();
                    List<Node> nodes = pane.getChildren();

                    if (last == 0.0) {
                        for (Node node : nodes) {
                            if (node instanceof ToolBar) {
                                ToolBar bar = (ToolBar) node;
                                last = bar.getLayoutX() + bar.getWidth() + 5.0;
                            }
                        }
                    } else {
                        last += 1.0;
                    }
                    text.setX(last);
                    text.setY(5.0);
                    text.setText(msg);
                    pane.getChildren().add(text);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void onServer(ActionEvent e) {
        serverRun = true;
        playersRun = false;
        gameRun = false;
        consoleRun = false;
    }

    @FXML
    private void onPlayers(ActionEvent e) {
        serverRun = false;
        playersRun = true;
        gameRun = false;
        consoleRun = false;
    }

    @FXML
    private void onGame(ActionEvent e) {
        serverRun = false;
        playersRun = false;
        gameRun = true;
        consoleRun = false;
    }

    @FXML
    public void settingLineHeight(ActionEvent actionEvent) {
        int lineHeight;
        try{
            lineHeight = Integer.valueOf(lineHeightTextField.getText());
        }catch(Exception e){
            lineHeightTextField.setText("长度非纯数字，请重新输入");
            return;
        }
        serverLogger.getConsoleOutputStream().setLineHeight(lineHeight);
    }

    @FXML
    private void scollCheck(ActionEvent actionEvent) {
        serverLogger.getConsoleOutputStream().setScroll(!scollCheckBox.isSelected());
    }
}
