package com.myproject.mysqljdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Test { 
    public static void main(String[] args) {
        DriverManager dbConnection = new DriverManager(); 
        Connection connection = null;
        Scanner scanner = new Scanner(System.in); 

        try {
            connection = dbConnection.connect();
            System.out.println("Connexion réussie à la base de données !");

            ExoJDBC exoJDBC = new ExoJDBC(connection); 
            exoJDBC.insertData();
            exoJDBC.displayMaxScripts();
            exoJDBC.displayTotalScripts();
            exoJDBC.calculateWeeklyScripts();
            exoJDBC.calculateScriptsByDeveloper(scanner); 
            exoJDBC.executeUserQuery(scanner); 
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        } finally {
          
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                scanner.close(); 
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
