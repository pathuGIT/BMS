package org.example;

import org.example.utill.DatabaseConnection;

import java.sql.Connection;

public class TestDatabaseConnction {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();

        if(connection != null){
            System.out.println("Connection success..");
        }else {
            System.out.println("Connection Failed..");
        }
    }
}
