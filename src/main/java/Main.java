import models.*;
import repository.DAOTicket;
import repository.DAOUser;

import java.util.List;


public class Main {

    public static void main(String[] args) {


        User user1 = new User();
        System.out.println(user1.getId());
        System.out.println(user1);
        Admin user2 = new Admin("Peta", Role.ADMIN);
        System.out.println(user2);
        User user3 = new User();
        System.out.println(user3);
        Client user4 = new Client("Vita", Role.USER);
        System.out.println(user4);

        Ticket ticket1 = new Ticket("club", (short) 182, Sector.B, true, user2.getId(), TicketType.DAY);
        Ticket ticket2 = new Ticket("train", (short) 345, Sector.A, false, user4.getId(), TicketType.MONTH);
        Ticket ticket3 = new Ticket("Buss", (short) 678, Sector.C, false, user4.getId(), TicketType.YEAR);

        PhoneNumber phoneNumber1 = new PhoneNumber("+375291111111");
        PhoneNumber phoneNumber2 = new PhoneNumber("+375292222222");

        Email email = new Email("qqq@qwert.com");

        new DAOUser().addUser(user2);
        new DAOUser().addUser(user4);

        new DAOTicket().addTicket(ticket1);
        new DAOTicket().addTicket(ticket2);
        new DAOTicket().addTicket(ticket3);

        System.out.println(new DAOUser().getUserById(user2.getId()));
        System.out.println(new DAOTicket().getTicketById(ticket2.getId()));

        user2.setName("Klara");
        ticket2.setStadiumSector(Sector.C);

        new DAOUser().updateUser(user2);
        System.out.println(new DAOUser().getUserById(user2.getId()));

        new DAOTicket().updateTicket(ticket2);
        System.out.println(new DAOTicket().getTicketById(ticket2.getId()));

        System.out.println("__________________");
        System.out.println("List tickets: ");
        List<Ticket> list = new DAOTicket().getTicketByUserId(user4.getId());
        list.forEach(System.out::println);


    }
}
