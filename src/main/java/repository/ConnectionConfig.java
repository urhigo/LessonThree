package repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = ConnectionConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IOException("Unable to find database.properties");
            }
            properties.load(input);
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = loadProperties();
        String jdbcUrl = properties.getProperty("jdbcUrl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
