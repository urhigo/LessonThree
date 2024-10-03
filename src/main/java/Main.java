import models.Ticket;
import services.TicketService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Ticket> listTickets = new TicketService().ticketsStorage();
        new TicketService().getInformationAboutTicketById((short) 1, listTickets);
        new TicketService().getInformationAboutTicketById((short) 44, listTickets);
    }
}
