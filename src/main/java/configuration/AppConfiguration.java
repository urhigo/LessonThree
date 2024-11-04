package configuration;

import models.User;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.ConnectionConfig;
import repository.DAOTicket;
import repository.DAOUser;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class AppConfiguration {


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

    @Bean
    public DataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(loadProperties().getProperty("jdbcUrl"));
        dataSource.setUsername(loadProperties().getProperty("username"));
        dataSource.setPassword(loadProperties().getProperty("password"));
        return dataSource;
    }

    @Bean
    public DAOUser userDAO() throws IOException {
        return new DAOUser(dataSource());
    }

    @Bean
    public DAOTicket ticketDAO() throws IOException {
        return new DAOTicket(dataSource());
    }
}
