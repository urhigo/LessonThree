package repository;

import models.Ticket;
import models.TicketType;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTicket {

    private static final String SQL_COMMAND_ADD = "INSERT INTO ticket (id, user_id, ticket_type, creation_date) VALUE (?, ?, ?, ?)";
    private static final String SQL_COMMAND_GET_BY_ID = "SELECT * FROM ticket WHERE id = ?;";
    private static final String SQL_COMMAND_UPDATE = "UPDATE ticket SET id = ?, ticket_type = ?, user_id = ?, creation_date = ? WHERE id = ?";
    private static final String SQL_COMMAND_DELETE = "DELETE FROM ticket WHERE id = ?";

    public void addTicket(Ticket ticket) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_ADD)) {
            statement.setString(1, String.valueOf(ticket.getId()));
            statement.setString(2, String.valueOf(ticket.getTicketType()));
            statement.setString(3, String.valueOf(ticket.getUserId()));
            statement.setString(4, String.valueOf(ticket.getTimeCreateTicket()));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket getTicketById(String id) {
        try (Connection connection = ConnectionConfig.getConnection();
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
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Ticket> getTicketByUserId(String id) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_COMMAND_GET_BY_ID)) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();

                ticket.setId(resultSet.getString("id"));
                ticket.setTicketType(TicketType.valueOf(resultSet.getString("ticket_type")));
                ticket.setUserId(resultSet.getString("user_id"));

                tickets.add(ticket);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_UPDATE)) {
            statement.setString(1, String.valueOf(ticket.getId()));
            statement.setString(2, String.valueOf(ticket.getTicketType()));
            statement.setString(3, String.valueOf(ticket.getUserId()));
            statement.setString(4, String.valueOf(ticket.getTimeCreateTicket()));
            statement.setString(5, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTicket(String id) {
        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COMMAND_DELETE)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
