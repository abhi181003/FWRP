package com.FWRP.DBconnection;

//DBConnector.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
 private static final String URL = "jdbc:mysql://localhost:3306/FWRP";
 private static final String USERNAME = "root";
 private static final String PASSWORD = "Deepxraman988!";

 public static Connection getConnection() throws SQLException {
     return DriverManager.getConnection(URL, USERNAME, PASSWORD);
 }
}
