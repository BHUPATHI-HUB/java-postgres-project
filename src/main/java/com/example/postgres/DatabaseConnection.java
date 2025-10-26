package com.example.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DatabaseConnection class for managing PostgreSQL database connections.
 * This class provides methods to establish and close connections to a PostgreSQL database.
 */
public class DatabaseConnection {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    
    /**
     * Constructor to initialize database connection parameters.
     * 
     * @param host Database host address
     * @param port Database port number
     * @param dbName Database name
     * @param user Database username
     * @param password Database password
     */
    public DatabaseConnection(String host, String port, String dbName, String user, String password) {
        this.dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        this.dbUser = user;
        this.dbPassword = password;
    }
    
    /**
     * Establishes a connection to the PostgreSQL database.
     * 
     * @return Connection object
     * @throws SQLException if connection fails
     * @throws ClassNotFoundException if PostgreSQL driver is not found
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER);
        Properties props = new Properties();
        props.setProperty("user", dbUser);
        props.setProperty("password", dbPassword);
        
        Connection connection = DriverManager.getConnection(dbUrl, props);
        System.out.println("Successfully connected to PostgreSQL database!");
        return connection;
    }
    
    /**
     * Closes the database connection.
     * 
     * @param connection Connection object to close
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
