package com.example.postgres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PostgresApp - Main application class demonstrating PostgreSQL database operations.
 * This application connects to PostgreSQL and performs basic database operations.
 */
public class PostgresApp {
    
    public static void main(String[] args) {
        // Database connection parameters
        String host = "localhost";
        String port = "5432";
        String dbName = "mydb";
        String user = "postgres";
        String password = "password";
        
        DatabaseConnection dbConnection = new DatabaseConnection(host, port, dbName, user, password);
        Connection connection = null;
        
        try {
            // Establish connection
            connection = dbConnection.getConnection();
            
            // Create a statement
            Statement statement = connection.createStatement();
            
            // Example: Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'users' created successfully!");
            
            // Example: Insert data
            String insertSQL = "INSERT INTO users (username, email) VALUES ('john_doe', 'john@example.com')";
            statement.executeUpdate(insertSQL);
            System.out.println("Data inserted successfully!");
            
            // Example: Query data
            String selectSQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            
            System.out.println("\nQuerying data from users table:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Username: " + username + ", Email: " + email);
            }
            
            // Close resources
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Always close the connection
            dbConnection.closeConnection(connection);
        }
    }
}
