package com.myproject.mysqljdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class DriverManager { 
    private static final String URL = "jdbc:mysql://localhost:3306/basef"; 
    private static final String USER = "root";
    private static final String PASSWORD = "";

   
    public Connection connect() throws SQLException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trouvé. Assurez-vous que le driver est dans le classpath.");
            throw new SQLException(e);
        }

    
        return java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
    }
}