package org.example.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String user = "root";
    private static final String pswd = "1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,pswd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
