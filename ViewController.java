package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private TextField pg_name;
    @FXML
    private TextField pg_programme;
    @FXML
    private TextField pg_branch;
    @FXML
    private TextField pg_block;
    @FXML
    private TextField pg_hallno;
    @FXML
    private TextField pg_sem;
    @FXML
    private TextField pg_rollno;

    private String uname;
    private String uprogramme,ubranch,ublock,uhallno,usem,urollno;

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
            uprogramme=rs.getString("Program");
            ubranch=rs.getString("Department");
            ublock = rs.getString("Block");
            uhallno = rs.getString("Hall NO");
            usem = rs.getString("Semester");
            urollno = rs.getString("username");
            pg_name.setText(uname);
            pg_programme.setText(uprogramme);
            pg_branch.setText(ubranch);
            pg_block.setText(ublock);
            pg_hallno.setText(uhallno);
            pg_sem.setText(usem);
            pg_rollno.setText(urollno);
        }

    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBregister.changeScene(event, "Logged-in.fxml", "Welcome!", null, null, null, null, null,null);
            }
        });
    }
}
