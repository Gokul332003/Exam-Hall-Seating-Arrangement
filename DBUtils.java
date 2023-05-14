package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class DBUtils {
    private static String name;
    public String getName(){
        return name;
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String email){
        Parent root = null;

        if(username != null && email != null){
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
              //  LoggedInController loggedInController = loader.getController();
                //loggedInController.setUserInformation(String username);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
              try{
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1530, 790));
        stage.show();
    }
    public static  void signUpUser(ActionEvent event, String username, String password,String confirm_Password,String email){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "Gokul@332003");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User Already Exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username,password,confirm_Password, Email_ID) VALUES(?,?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, confirm_Password);
                psInsert.setString(4, email);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Sign Up Successful \n    Please Login to continue  ");
                alert.show();
                changeScene(event, "sample.fxml", "Welcome!", username, email);
//                while(resultSet.next()) {
//                    String retrievedPassword = resultSet.getString("password");
//                    String retrievedcon_password = resultSet.getString("confirm_Password");
//                    if (retrievedPassword.equals(retrievedcon_password)) {
//                        System.out.println("Password Matched");
//                    } else {
//                        System.out.println("Password not matched");
//                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
//                        alert1.setContentText("The provided credentials are incorrect");
//                        alert.show();
//                    }
//                }

                    while (resultSet.next()) {
                        String retrievedPassword = resultSet.getString("password");
                        String retrievedconpassword = resultSet.getString("confirm_Password");
                        if (retrievedPassword.equals(confirm_Password)) {
                          System.out.println("Password Matched");
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setContentText("Password not matched");
                            alert1.show();
                        }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null){
                try{
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try{
                    psInsert.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video","root", "Gokul@332003");
            preparedStatement = connection.prepareStatement("SELECT password, Email_ID FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided Credientials are incorrect");
                alert.show();
            }else{
                while(resultSet.next()){
                    name = username;
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedemail = resultSet.getString("Email_ID");
                    if(retrievedPassword.equals(password)){
                        changeScene(event, "Logged-in.fxml", "Welcome!", username,retrievedemail);
                    }else{
                        System.out.println("Password not matched");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
