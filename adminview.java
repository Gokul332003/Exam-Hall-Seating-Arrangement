package sample;

import com.sun.javafx.event.EventHandlerManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class adminview implements Initializable {


    @FXML
    private TextField pg_name;
    @FXML
    private TextField pg_rollno;
    @FXML
    private TextField pg_block;
    @FXML
    private TextField pg_hallno;

    @FXML
    private Button button_publish;

    private String uname,urollno;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            try{
                DBUtils cc = new DBUtils();
                String user = cc.getName();

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "Gokul@332003");
                PreparedStatement ps=con.prepareStatement("select * from register WHERE username=?");
                ps.setString(1,user);
                ResultSet rs = ps.executeQuery();

                while (rs.next())
                {
                    uname=rs.getString("name");
                    urollno = rs.getString("username");
                    pg_name.setText(uname);
                    pg_rollno.setText(urollno);

                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        button_publish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });
    }
}
