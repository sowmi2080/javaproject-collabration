package com.example.hotelreservationsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    private Stage stage;
    private Parent root;
    private Scene scene;



    @FXML
    private Button LoginButton;

    @FXML
    private Button BackLoginButton;

    @FXML
    private Button ForgetButton;

    @FXML
    private Button SignupButton;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    ResultSet res = null;



//    private boolean isValidCredentials(String username, String password) {
//        // Replace this with your actual authentication logic
//        // For simplicity, let's assume the correct username is "user" and the password is "pass"
//        return username.equals("user") && password.equals("pass");
//    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    //this function to navigate after login
    public void openHomePage(MouseEvent event) throws IOException {
        try{
            JDBC con = new JDBC();
            //check username password validation here
            String username = usernameField.getText();
            String password = passwordField.getText();
            String q = "select * from login where username = '"+username+"' and  password = '"+password+"'";
            ResultSet res = con.statement.executeQuery(q);
            if (res.next()){
                showAlert("Login Successful", "Welcome, " + username + "!");
                root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // Replace this with your actual authentication logic
//        if (isValidCredentials(username, password)) {
//            showAlert("Login Successful", "Welcome, " + username + "!");
//            root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//
//        } else {
//            showAlert("Login Failed", "Invalid username or password.");
//        }

    }


    public void openRegisterPage(MouseEvent event1) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openForgetPasswordPage(MouseEvent event2) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ForgetEmail.fxml"));
        stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}