module com.example.hotelreservationsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires mysql.connector.j;
    requires java.sql;

    opens com.example.hotelreservationsystem to javafx.fxml;
    exports com.example.hotelreservationsystem;
}