package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class DBregister {


    public static void changeScene(ActionEvent event, String fxmlFile, String title, String name,String rono, String Dob, String program, String department, String semester) {
        Parent root = null;

        if (name != null && rono!= null && Dob != null && program != null && department != null && semester != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBregister.class.getResource(fxmlFile));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
          try { root = FXMLLoader.load(DBregister.class.getResource(fxmlFile));
          } catch (IOException e) {
         e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1530, 790));
        stage.show();
    }

    public static void RegisterUser(ActionEvent event, String name,String rollno, String DOB, String Program, String Department, String Semester) {

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "Gokul@332003");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM register WHERE ID = ?");
            psCheckUserExists.setString(1, name);
            resultSet = psCheckUserExists.executeQuery();

            psInsert = connection.prepareStatement("INSERT INTO register (name,username,DOB,Program,Department,Semester) VALUES(?,?,?,?,?,?)");
            psInsert.setString(1, name);
            psInsert.setString(2,rollno);
            psInsert.setString(3, DOB);
            psInsert.setString(4, Program);
            psInsert.setString(5, Department);
            psInsert.setString(6, Semester);

            psInsert.executeUpdate();
            System.out.println("Submitted Succesfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}