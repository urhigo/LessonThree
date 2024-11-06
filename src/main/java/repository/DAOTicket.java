package repository;

import models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DAOTicket {

    private static final String SQL_COMMAND_ADD = "INSERT INTO ticket (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
    private static final String SQL_COMMAND_GET_BY_ID = "SELECT * FROM ticket WHERE id = ?;";
    private static final String SQL_COMMAND_UPDATE = "UPDATE ticket SET id = ?, ticket_type = ?::ticket_type, user_id = ?, creation_date = ? WHERE id = ?";
    private static final String SQL_COMMAND_DELETE = "DELETE FROM ticket WHERE id = ?";
    private static final String SQL_COMMAND_GET_BY_USER_ID = "SELECT * FROM ticket WHERE user_id = ?;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DAOTicket(JdbcTemplate transaction) {
        this.jdbcTemplate = transaction;
    }

    @Transactional
    public void addTicket(Ticket ticket) {
        jdbcTemplate.update(SQL_COMMAND_ADD, ticket.getId(), ticket.getUserId(),
                ticket.getTicketType().name(), ticket.getTimeCreateTicket());
    }

    @Transactional
    public Ticket getTicketById(String id) {
        return jdbcTemplate.queryForObject(SQL_COMMAND_GET_BY_ID, new TicketRowMapper(), id);
    }

    @Transactional
    public List<Ticket> getTicketByUserId(String id) {
        return jdbcTemplate.query(SQL_COMMAND_GET_BY_USER_ID, new TicketRowMapper(), id);
    }

    @Transactional
    public void updateTicket(Ticket ticket) {
        jdbcTemplate.update(SQL_COMMAND_UPDATE, ticket.getId(), ticket.getTicketType().name(),
                ticket.getUserId(), ticket.getTimeCreateTicket(), ticket.getId());
    }

    @Transactional
    public void deleteTicket(String id) {
        jdbcTemplate.update(SQL_COMMAND_DELETE, id);
    }
}
