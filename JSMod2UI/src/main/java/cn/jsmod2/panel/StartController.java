package cn.jsmod2.panel;

import cn.jsmod2.core.Console;
import cn.jsmod2.core.log.ServerLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author magiclu550
 * @author GNX-Susanoo
 */

public class StartController {

    private ServerLogger serverLogger = ServerLogger.getLogger();
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

    @FXML
    public Button send;

    @FXML
    public TextField sendText;

    @FXML
    private void initialize(){
        serverLogger.getConsoleOutputStream().setTextArea(consoleTextArea);
    }

    @FXML
    public void onSend(ActionEvent event){
        String text = sendText.getText();
        Console.getConsole().runConsoleCommandWithEmerald(text).toString();
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
