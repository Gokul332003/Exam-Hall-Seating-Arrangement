package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_sign_up;

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_conpassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() &&! tf_conpassword.getText().trim().isEmpty() && !tf_email.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText(),tf_conpassword.getText(), tf_email.getText());
                } else {
                    System.out.println("Please fill all the information.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up.");
                    alert.show();
                }

            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml","Log in",null,null);
            }
        });
    }
}
