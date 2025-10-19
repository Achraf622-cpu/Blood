package com.donner.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String ENV_URL = "DB_URL";
    private static final String ENV_USERNAME = "DB_USERNAME";
    private static final String ENV_PASSWORD = "DB_PASSWORD";

    // Path to local properties file (relative to project root). Adjust if needed.
    private static final String LOCAL_PROPERTIES_PATH = "config/db.properties";

    private static String url;
    private static String username;
    private static String password;

    static {
        loadConfiguration();
    }

    private static void loadConfiguration() {
        // Hardcoded local development configuration (simple and works out of the box)
        // Adjust these three lines if your local DB is different
        url = "jdbc:postgresql://localhost:5432/blood_donation";
        username = "postgres";
        password = "password";
    }

    public static Connection getConnection() {
        if (url == null || username == null || password == null) {
            return null;
        }

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL driver not found: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage() +
                    " (url=" + url + ", user=" + username + ")");
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
