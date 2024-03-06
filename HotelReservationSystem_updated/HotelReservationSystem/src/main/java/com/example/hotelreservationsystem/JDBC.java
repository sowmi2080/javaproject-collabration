package com.example.hotelreservationsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class JDBC {

    public static Connection connection;
    public static Statement statement;

    public static void connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/HotelReservationSystem",
                    "root",
                    ""
            );
            statement = connection.createStatement();
            if(connection!=null){
                System.out.println("Success db connection");
            }
            else{
                System.out.println("Failed db connection");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
