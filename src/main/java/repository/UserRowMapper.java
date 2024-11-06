package repository;

import models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        String creationDate = rs.getString("creation_date");
        LocalDateTime dateTime;
        if (creationDate.contains("T")) {
            dateTime = LocalDateTime.parse(creationDate);
        } else {
            String formattedCreationDate = creationDate.replace(" ", "T");
            dateTime = LocalDateTime.parse(formattedCreationDate);
        }
        user.setTimeCreationUser(dateTime);
        return user;
    }
}
