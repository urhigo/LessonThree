package repository;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DAOUser {

    private static final String SQL_COMMAND_ADD = "INSERT INTO _user (id, name, creation_date) VALUES (?, ?, ?)";
    private static final String SQL_COMMAND_GET_BY_ID = "SELECT * FROM _user WHERE id = ?;";
    private static final String SQL_COMMAND_UPDATE = "UPDATE _user SET id = ?, name = ?, creation_date = ? WHERE id = ?";
    private static final String SQL_COMMAND_DELETE = "DELETE FROM _user WHERE id = ?";


    private final DataSource dataSource;

    @Autowired
    public DAOUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addUser(User user) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_ADD);
            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, user.getName());
            statement.setString(3, String.valueOf(user.getTimeCreationUser()));
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_GET_BY_ID)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User userFromDB = new User();
                    userFromDB.setId(resultSet.getString("id"));
                    userFromDB.setName(resultSet.getString("name"));
                    return userFromDB;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void updateUser(User user){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_UPDATE)) {
            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, String.valueOf(user.getName()));
            statement.setString(3, String.valueOf(user.getTimeCreationUser()));
            statement.setString(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_DELETE)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
