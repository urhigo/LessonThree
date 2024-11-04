package repository;

import configuration.AppConfiguration;
import models.Ticket;
import models.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DAOTicket {

    private static final String SQL_COMMAND_ADD = "INSERT INTO ticket (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
    private static final String SQL_COMMAND_GET_BY_ID = "SELECT * FROM ticket WHERE id = ?;";
    private static final String SQL_COMMAND_UPDATE = "UPDATE ticket SET id = ?, ticket_type = ?, user_id = ?, creation_date = ? WHERE id = ?";
    private static final String SQL_COMMAND_DELETE = "DELETE FROM ticket WHERE id = ?";
    private static final String SQL_COMMAND_GET_BY_USER_ID = "SELECT * FROM ticket WHERE user_id = ?;";

    private final DataSource dataSource;

    @Autowired
    public DAOTicket(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addTicket(Ticket ticket) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_ADD)) {
            statement.setString(1, String.valueOf(ticket.getId()));
            statement.setString(2, String.valueOf(ticket.getUserId()));
            statement.setObject(3, ticket.getTicketType(), Types.OTHER);
            statement.setString(4, String.valueOf(ticket.getTimeCreateTicket()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket getTicketById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_GET_BY_ID)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ticket ticketFromDB = new Ticket();
                    ticketFromDB.setId(resultSet.getString("id"));
                    ticketFromDB.setUserId(resultSet.getString("user_id"));
                    ticketFromDB.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                    return ticketFromDB;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Ticket> getTicketByUserId(String id) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_GET_BY_USER_ID)){
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getString("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setUserId(resultSet.getString("user_id"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_UPDATE)) {
            statement.setString(1, String.valueOf(ticket.getId()));
            statement.setObject(2, ticket.getTicketType(), Types.OTHER);
            statement.setString(3, String.valueOf(ticket.getUserId()));
            statement.setString(4, String.valueOf(ticket.getTimeCreateTicket()));
            statement.setString(5, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTicket(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_DELETE)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
