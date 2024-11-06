package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import repository.ConnectionConfig;
import repository.DAOTicket;
import repository.DAOUser;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource) {
        return new TransactionTemplate(transactionManager(dataSource));
    }

    @Bean
    public DAOUser userDAO() throws IOException, SQLException {
        return new DAOUser(jdbcTemplate(dataSource()));
    }

    @Bean public DAOTicket ticketDAO() throws IOException {
        return new DAOTicket(jdbcTemplate(dataSource()));
    }
}
