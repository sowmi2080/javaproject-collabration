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
import java.sql.ResultSet;
import java.time.LocalDate;

public class PaymentController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField BookingIDField;

    @FXML
    private TextField amountPayableField;

    @FXML
    private RadioButton creditDebitField;

    @FXML
    private RadioButton cashField;

    @FXML
    private DatePicker paymentDateField;

    @FXML
    private TextField confirmationEmailField;

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

    public void proceedPayment(MouseEvent event) throws IOException {
        String BookingID = BookingIDField.getText();
        String AmountPayable = amountPayableField.getText();
        String confirmationEmail = confirmationEmailField.getText();
        String PaymentMethod = null;
        if (this.creditDebitField.isSelected()) {
            PaymentMethod = "Credit/Debit";
        } else if (this.cashField.isSelected()) {
            PaymentMethod = "Cash";
        }
        LocalDate paymentDate = paymentDateField.getValue();
        try {
            JDBC con = new JDBC();
            if (BookingID.equals("")||AmountPayable.equals("")||confirmationEmail.equals("")||PaymentMethod.equals("")||paymentDate.equals("")) {
                showAlert("All fields not filled", "please fill all fields for payment to proceed!");
            } else {
                String q1 = "select bookingID from booking where bookingID = '"+BookingID+"'";
                ResultSet res = con.statement.executeQuery(q1);
                if (res.next()){
                    String q = "insert into payment(paymentMethod,bookingID,payableAmount,paymentDate,confirmationEmail) values('" + PaymentMethod + "', '" + BookingID + "','" + AmountPayable + "','" + paymentDate + "','" + confirmationEmail + "' )";
                    con.statement.executeUpdate(q);
                    System.out.println("Payment done successfully");
                    showAlert("Payment Successful", "Payment completed Successfully");
                    root = FXMLLoader.load(getClass().getResource("AfterSignIn.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    showAlert("Payment Not Successful", "Payment not completed Successfully(check booking id)");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
