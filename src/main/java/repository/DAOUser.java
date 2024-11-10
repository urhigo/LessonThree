package repository;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Repository
public class DAOUser {

    private static final String SQL_COMMAND_ADD = "INSERT INTO _user (id, name, creation_date) VALUES (?, ?, ?)";
    private static final String SQL_COMMAND_GET_BY_ID = "SELECT * FROM _user WHERE id = ?;";
    private static final String SQL_COMMAND_UPDATE = "UPDATE _user SET id = ?, name = ?, creation_date = ? WHERE id = ?";
    private static final String SQL_COMMAND_DELETE = "DELETE FROM _user WHERE id = ?";


    private final JdbcTemplate transactionTemplate;

    @Autowired
    public DAOUser(JdbcTemplate transaction) {
        this.transactionTemplate = transaction;
    }

    public void addUser(User user) {
        transactionTemplate.update(SQL_COMMAND_ADD, user.getId(), user.getName(),
                String.valueOf(user.getTimeCreationUser()));
    }

    public User getUserById(String id) {
        return transactionTemplate.queryForObject(SQL_COMMAND_GET_BY_ID, new UserRowMapper(), id);
    }

    public void deleteUser(String id) {
        transactionTemplate.update(SQL_COMMAND_DELETE, id);
    }

    public void updateUser(User user) {
        transactionTemplate.update(SQL_COMMAND_UPDATE, user.getId(), user.getName(), user.getTimeCreationUser(), user.getId());
    }
}
