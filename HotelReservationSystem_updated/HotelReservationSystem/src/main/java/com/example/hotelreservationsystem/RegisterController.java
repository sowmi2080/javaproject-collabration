package com.example.hotelreservationsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;


public class RegisterController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField staffIDField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField contactNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField designationField;

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void openLoginPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openHomePage(MouseEvent event) throws IOException {
            //check username password validation here
            String staffID = staffIDField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String contactNumber = contactNumberField.getText();
            String email = emailField.getText();
            String designation = designationField.getText();
            try {
                JDBC con = new JDBC();
                if (staffID.equals("")||username.equals("")||password.equals("")||contactNumber.equals("")||email.equals("")||designation.equals("")) {
                    showAlert("All fields not filled", "please fill all fields for registration!");
                } else {
                    String q1 = "insert into login values('" + username + "', '" + staffID + "','" + password + "','" + email + "','" + contactNumber + "','" + designation + "' )";
                    con.statement.executeUpdate(q1);
                    System.out.println("Registration of user successful");
                    showAlert("Registration is Successful", "Welcome, " + username + "!");
                    root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
