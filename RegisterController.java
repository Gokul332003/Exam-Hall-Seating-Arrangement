package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private Button button_back;

    @FXML
    private Button button_submit;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_dob;

    @FXML
    private TextField tf_program;

    @FXML
    private TextField tf_dep;

    @FXML
    private TextField tf_sem;

    @FXML
    private TextField tf_rollno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "Logged-in.fxml", "Registeration", null, null);
            }
        });
        button_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBregister.RegisterUser(event, tf_name.getText(),tf_rollno.getText(), tf_dob.getText(), tf_program.getText(), tf_dep.getText(), tf_sem.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Do you want to submit ?");
                alert.show();
                DBUtils.changeScene(event,"register.fxml","Registeration",null,null);
            }
        });
    }
}