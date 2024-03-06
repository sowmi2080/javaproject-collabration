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

public class ForgetPasswordController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField newPassword1;

    @FXML
    private PasswordField newPassword2;

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

    public void openRegisterPage(MouseEvent event1) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openAfterPasswordReset(MouseEvent event1) throws IOException {
        String username = usernameField.getText();
        String newPassword = newPassword1.getText();
        String newReTypedPassword = newPassword2.getText();
        try {
            JDBC con = new JDBC();
            String q = "select * from login where username = '"+username+"'";
            ResultSet res = con.statement.executeQuery(q);
            if(res.next()) {
                if (!Objects.equals(newPassword, newReTypedPassword)) {
                    showAlert("Incorrect password", "check and type correct password");
                } else {
                    String q1 = "update login set password='"+newPassword+"'where username='" +username+"'";
                    con.statement.executeUpdate(q1);
                    System.out.println("password reset user successful");
                    showAlert("Password Reset Successful", "Successful password reset.Login again");
                    root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else {
                showAlert("Username Mismatch", "Invalid username");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
