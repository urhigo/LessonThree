import models.Ticket;
import services.MyArrayList;
import services.TicketService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Ticket> listTickets = new TicketService().ticketsStorage();
        new TicketService().getInformationAboutTicketById((short) 1, listTickets);
        new TicketService().getInformationAboutTicketById((short) 44, listTickets);

        MyArrayList<String> listDays = new MyArrayList<>();
        listDays.add("One");
        listDays.add("Two");
        listDays.add("Three");

        System.out.println(listDays.get(2));

        listDays.remove(1);

        System.out.println(listDays.get(1));
    }
}
