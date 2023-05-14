package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Handler;


public class AdminController implements Initializable{

    @FXML
    private Button button_log;

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_mail;

    @FXML
    private TextField tf_pass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_log.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"sample.fxml","log in",null,null);
            }

            });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"adminview.fxml","Update",null,null);
            }

        });

    }
}
