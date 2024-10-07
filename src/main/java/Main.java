import models.*;
import services.TicketService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Ticket> listTickets = new TicketService().ticketsStorage();
        new TicketService().getInformationAboutTicketById((short) 1, listTickets);
        new TicketService().getInformationAboutTicketById((short) 44, listTickets);

        Admin user1 = new Admin(1, "Peta", Role.ADMIN);
        user1.getUserRole(user1);
        user1.printInformationAboutObject(user1);
        Client user2 = new Client(2, "Vita", Role.USER);
        user1.getUserRole(user2);
        user1.printInformationAboutObject(user2);

    }
}
