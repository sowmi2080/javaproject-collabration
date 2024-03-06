package com.example.hotelreservationsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class BookingController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerEmailField;

    @FXML
    private TextField customerContactField;

    @FXML
    private RadioButton twoBedField;

    @FXML
    private RadioButton fourBedField;

    @FXML
    private RadioButton familyField;

    @FXML
    private RadioButton vipField;

    @FXML
    private DatePicker checkInField;

    @FXML
    private TextField noOfPaxField;

    public void openHomePage(MouseEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openBookingPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Booking.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void confirmBooking(MouseEvent event) throws IOException {
        String customerName = customerNameField.getText();
        String customerEmail = customerEmailField.getText();
        String customerContact = customerContactField.getText();
        String RoomType = null;
        if (this.twoBedField.isSelected()) {
            RoomType = "2-Bed";
        } else if (this.fourBedField.isSelected()) {
            RoomType = "4-Bed";
        }
        else if (this.familyField.isSelected()) {
            RoomType = "Family";
        }
        else if (this.vipField.isSelected()) {
            RoomType = "VIP";
        }
        LocalDate checkInDate = checkInField.getValue();
        String totalNoPax = noOfPaxField.getText();
        try {
            JDBC con = new JDBC();
            if (customerName.equals("")||customerEmail.equals("")||customerContact.equals("")||RoomType.equals("")||totalNoPax.equals("")||checkInDate.equals("")) {
                showAlert("All fields not filled", "please fill all fields for booking!");
            } else {
                String q = "insert into booking(customerName,customerEmail,customerContact,roomType,checkInDate,totalPax) values('" + customerName + "', '" + customerEmail + "','" + customerContact + "','" + RoomType + "','" + checkInDate + "','" + totalNoPax + "' )";
                con.statement.executeUpdate(q);
                System.out.println("Booking is successful");
                showAlert("Booking Successful", "Proceed to payment");
                root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void openPaymentPage(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
