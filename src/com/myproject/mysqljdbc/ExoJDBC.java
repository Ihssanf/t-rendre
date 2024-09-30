package com.myproject.mysqljdbc;

import java.sql.*;
import java.util.Scanner;

public class ExoJDBC { 
    private final Connection connection;

 
    public ExoJDBC(Connection connection) {
        this.connection = connection;
    }

   
    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS DevData (" +
                "Developpeurs VARCHAR(32) NOT NULL, " +
                "Jour VARCHAR(11) NOT NULL, " +
                "NbScripts INTEGER NOT NULL" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'DevData' créée ou déjà existante.");
        }
    }

    
    public void insertData() throws SQLException {
        String deleteSQL = "DELETE FROM DevData";
        String insertSQL = "INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('%s', '%s', %d)";

        String[][] data = {
                {"ALAMI", "Lundi", "1"},
                {"WAFI", "Lundi", "2"},
                {"SLAMI", "Mardi", "9"},
                {"SAFI", "Mardi", "2"},
                {"ALAMI", "Mardi", "2"},
                {"SEBIHI", "Mercredi", "2"},
                {"WAFI", "Jeudi", "3"},
                {"ALAOUI", "Vendredi", "9"},
                {"WAFI", "Vendredi", "3"},
                {"SEBIHI", "Vendredi", "4"}
        };

       
        try (Statement stmt = connection.createStatement()) {
            int rowsDeleted = stmt.executeUpdate(deleteSQL);
            System.out.println(rowsDeleted + " lignes supprimées de 'DevData'.");
        }

       
        try (Statement stmt = connection.createStatement()) {
            for (String[] row : data) {
                try {
                    int nbScripts = Integer.parseInt(row[2]);
                    String formattedSQL = String.format(insertSQL, row[0], row[1], nbScripts);
                    stmt.executeUpdate(formattedSQL);
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de format pour NbScripts: " + row[2] + ". Défaut à 0.");
                    String formattedSQL = String.format(insertSQL, row[0], row[1], 0);
                    stmt.executeUpdate(formattedSQL);
                }
            }
            System.out.println(data.length + " lignes insérées dans la table 'DevData'.");
        }
    }

  
    public void displayMaxScripts() throws SQLException {
        String maxScriptsQuery = "SELECT d.Developpeurs, d.Jour, d.NbScripts " +
                "FROM DevData d " +
                "INNER JOIN (" +
                "    SELECT Jour, MAX(NbScripts) AS MaxScripts " +
                "    FROM DevData " +
                "    GROUP BY Jour" +
                ") m ON d.Jour = m.Jour AND d.NbScripts = m.MaxScripts";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(maxScriptsQuery)) {

            System.out.println("\nPersonne ayant réalisé le nombre maximum de scripts en une journée :");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-15s %-10s %-10s%n", "Developpeurs", "Jour", "NbScripts");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                String dev = rs.getString("Developpeurs");
                String jour = rs.getString("Jour");
                int nbScripts = rs.getInt("NbScripts");
                System.out.printf("%-15s %-10s %-10d%n", dev, jour, nbScripts);
            }
        }
    }

  
    public void displayTotalScripts() throws SQLException {
        String totalScriptsQuery = "SELECT Developpeurs, SUM(NbScripts) AS TotalScripts " +
                "FROM DevData " +
                "GROUP BY Developpeurs " +
                "ORDER BY TotalScripts DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(totalScriptsQuery)) {

            System.out.println("\nListe des développeurs triée par le nombre total de scripts :");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-15s %-15s%n", "Developpeurs", "Total Scripts");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                String dev = rs.getString("Developpeurs");
                int totalScripts = rs.getInt("TotalScripts");
                System.out.printf("%-15s %-15d%n", dev, totalScripts);
            }
        }
    }

    
    public void calculateWeeklyScripts() throws SQLException {
        String weeklyScriptsQuery = "SELECT SUM(NbScripts) AS TotalScriptsSemaine FROM DevData";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(weeklyScriptsQuery)) {

            if (rs.next()) {
                int totalWeeklyScripts = rs.getInt("TotalScriptsSemaine");
                System.out.println("\nNombre total de scripts réalisés en une semaine : " + totalWeeklyScripts);
            }
        }
    }

   
    public void calculateScriptsByDeveloper(Scanner scanner) throws SQLException {
        System.out.print("\nEntrez le nom du programmeur pour voir le nombre total de scripts : ");
        String developerName = scanner.nextLine().trim();

        if (developerName.isEmpty()) {
            System.out.println("Le nom du développeur ne peut pas être vide.");
            return;
        }

        String query = "SELECT SUM(NbScripts) AS TotalScripts FROM DevData WHERE Developpeurs = '" + developerName + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                int totalScripts = rs.getInt("TotalScripts");
                if (rs.wasNull()) {
                    System.out.println("Aucun script trouvé pour le programmeur : " + developerName);
                } else {
                    System.out.println("Total de scripts réalisés par " + developerName + " : " + totalScripts);
                }
            } else {
                System.out.println("Aucun script trouvé pour le programmeur : " + developerName);
            }
        }
    }

    
    public void executeUserQuery(Scanner scanner) throws SQLException {
        System.out.print("\nEntrez une requête SQL : ");
        String userQuery = scanner.nextLine().trim();

        if (userQuery.isEmpty()) {
            System.out.println("Aucune requête entrée.");
            return;
        }

        try (Statement stmt = connection.createStatement()) {
            if (userQuery.toUpperCase().startsWith("SELECT")) {
                try (ResultSet rs = stmt.executeQuery(userQuery)) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    System.out.println("Nombre de colonnes : " + columnCount);
                    System.out.println("\nInformations sur les colonnes :");
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("Colonne %d: %s (%s)%n", i, metaData.getColumnName(i), metaData.getColumnTypeName(i));
                    }

                    System.out.println("\nContenu de la table :");
                    while (rs.next()) {
                        StringBuilder row = new StringBuilder();
                        for (int i = 1; i <= columnCount; i++) {
                            row.append(rs.getString(i)).append(" | ");
                        }
                        
                        if (row.length() > 3) {
                            row.setLength(row.length() - 3);
                        }
                        System.out.println(row.toString());
                    }
                }
            } else {
                int rowsAffected = stmt.executeUpdate(userQuery);
                System.out.println("Nombre de lignes modifiées : " + rowsAffected);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }
}