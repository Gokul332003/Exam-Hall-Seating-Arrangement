package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private Button button_register;

    @FXML
    private Button button_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             DBUtils.changeScene(event, "sample.fxml", "Log in!", null,null);
            }
        });
         button_register.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 DBregister.changeScene(event,"register.fxml","Registeration",null,null,null,null,null,null);
             }
         });
         button_view.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 DBregister.changeScene(event,"view.fxml","Hall Details",null,null,null,null,null,null);
             }
         });
    }
}
