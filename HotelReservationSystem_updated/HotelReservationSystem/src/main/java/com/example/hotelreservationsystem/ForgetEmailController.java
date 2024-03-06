package com.example.hotelreservationsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class ForgetEmailController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField forgetEmailField;

    ResultSet res = null;

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    //this function to navigate after forget email page
    public void openForgetPassword(MouseEvent event) throws IOException {
        try{
            JDBC con = new JDBC();
            String forgetEmail = forgetEmailField.getText();
            String q = "select * from login where email = '"+forgetEmail+"'";
            ResultSet res = con.statement.executeQuery(q);
            if (res.next()){
                System.out.println("Forget email for forget password valid");
                root = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                showAlert("Invalid Forget Email", "Invalid email");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
