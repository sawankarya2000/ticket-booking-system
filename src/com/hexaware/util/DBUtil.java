package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getDBConn() {
        Connection conn = null;
        try {
//          String connectionString = "jdbc:mysql://localhost:3306/mydatabase?user=myuser&password=mypassword";
            String connectionString = DBPropertiesUtil.getConnectionString("src/resources/db.properties");
            conn = DriverManager.getConnection(connectionString);
            System.out.println("Connected");

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
