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
import java.time.LocalDate;

public class CancelBookingController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField cancelBookingIDField;
    public void openPaymentPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openHomePage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openBookingPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Booking.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void openSignOutPage(MouseEvent event) throws IOException {
        showAlert("Sign Out","Successful Logout. Thanks");
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openAboutUsPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openRoomPackagesPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RoomsPackages.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelBooking(MouseEvent event) throws IOException {
        String cancelBookingID = cancelBookingIDField.getText();
        try {
            JDBC con = new JDBC();
            if (cancelBookingID.equals("")) {
                showAlert("Empty Field", "Enter a Boooking ID to cancel!");
            } else {
                String q1 = "select bookingID from booking where bookingID = '"+cancelBookingID+"'";
                ResultSet res = con.statement.executeQuery(q1);
                if (res.next()){
                    String q = "delete from booking where bookingID = '"+cancelBookingID+"'";
                    con.statement.executeUpdate(q);
                    System.out.println("Deleted booking successfully");
                    showAlert("Cancel Booking Successful", "Booking ID "+cancelBookingID+" cancellation completed");
                    root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    showAlert("Invalid", "Unsuccessful cancellation.Enter a valid booking id");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
