package repository;

import models.Sector;
import models.Ticket;
import models.TicketType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TicketRowMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket("See", (short) 444, Sector.A, true, TicketType.YEAR);
        ticket.setId(rs.getString("id"));
        ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
        ticket.setUserId(rs.getString("user_id"));
        String creationDate = rs.getString("creation_date");
        LocalDateTime dateTime;
        if (creationDate.contains("T")) {
            dateTime = LocalDateTime.parse(creationDate);
        } else {
            String formattedCreationDate = creationDate.replace(" ", "T");
            dateTime = LocalDateTime.parse(formattedCreationDate);
        }
        ticket.setTimeCreateTicket(dateTime);
        return ticket;
    }
}
