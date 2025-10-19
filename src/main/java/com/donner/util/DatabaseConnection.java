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
        // 1) Try environment variables first (recommended for production / CI)
        url = System.getenv(ENV_URL);
        username = System.getenv(ENV_USERNAME);
        password = System.getenv(ENV_PASSWORD);

        if (url != null && username != null && password != null) {
            // Got all from environment - done
            return;
        }

        // 2) If not fully provided from env, try properties file
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(LOCAL_PROPERTIES_PATH)) {
            props.load(fis);
            // Use properties only if env var wasn't set (env takes precedence)
            if (url == null) {
                url = props.getProperty("db.url");
            }
            if (username == null) {
                username = props.getProperty("db.username");
            }
            if (password == null) {
                password = props.getProperty("db.password");
            }
        } catch (IOException e) {
            // properties file missing or unreadable
            System.out.println("Warning: could not load " + LOCAL_PROPERTIES_PATH + " (" + e.getMessage() + "). " +
                    "Falling back to environment variables if available.");
        }

        // 3) Final sanity-check: if anything missing, print error.
        if (url == null || username == null || password == null) {
            System.out.println("ERROR: Database configuration is incomplete. Make sure environment variables " +
                    ENV_URL + ", " + ENV_USERNAME + ", " + ENV_PASSWORD +
                    " are set, or create " + LOCAL_PROPERTIES_PATH + " with db.url, db.username, db.password.");
        }
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
            System.out.println("Database connection failed: " + e.getMessage());
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
