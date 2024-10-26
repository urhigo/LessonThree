package repositories;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user){
        String sqlCommand = "INSERT INTO _user (id, name, creation_date) VALUE (?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, user.getName());
            statement.setString(3, String.valueOf(user.getTimeCreateUser()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(String id){
        String sqlCommand = "SELECT * FROM _user WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
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


    public void updateUser(User user) throws SQLException {
        String sqlCommand = "UPDATE _user SET id = ?, name = ?, creation_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, String.valueOf(user.getName()));
            statement.setString(3, String.valueOf(user.getTimeCreateUser()));
            statement.setString(4, user.getId());
            statement.executeUpdate();
        }
    }

    public void deleteUser(String id) throws SQLException {
        String sqlCommand = "DELETE FROM _user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }
}
