package repositories;

import models.Ticket;
import models.TicketType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTicket(Ticket ticket){
        String sqlCommand = "INSERT INTO ticket (id, user_id, ticket_type, creation_date) VALUE (?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, String.valueOf(ticket.getId()));
            statement.setString(2, String.valueOf(ticket.getTicketType()));
            statement.setString(3, String.valueOf(ticket.getUserId()));
            statement.setString(4, String.valueOf(ticket.getTimeCreateTicket()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket getTicketById(String id){
        String sqlCommand = "SELECT * FROM ticket WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
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

    public List<Ticket> getTicketByUserId(String id){
        String sqlCommand = "SELECT * FROM ticket WHERE user_id = ?;";
        List<Ticket> tickets = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlCommand)) {
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

    public void updateTicket(Ticket ticket) throws SQLException {
        String sqlCommand = "UPDATE ticket SET id = ?, ticket_type = ?, user_id = ?, creation_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, String.valueOf(ticket.getId()));
            statement.setString(2, String.valueOf(ticket.getTicketType()));
            statement.setString(3, String.valueOf(ticket.getUserId()));
            statement.setString(4, String.valueOf(ticket.getTimeCreateTicket()));
            statement.setString(5, ticket.getId());
            statement.executeUpdate();
        }
    }

    public void deleteTicket(String id) throws SQLException {
        String sqlCommand = "DELETE FROM ticket WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }
}
