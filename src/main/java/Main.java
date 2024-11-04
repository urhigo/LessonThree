import configuration.AppConfiguration;
import models.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.DAOTicket;
import repository.DAOUser;

import java.util.List;


public class Main {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        DAOUser userServices = context.getBean(DAOUser.class);
        DAOTicket ticketServices = context.getBean(DAOTicket.class);

        User user1 = new User();
        System.out.println(user1.getId());
        System.out.println(user1);
        Admin user2 = new Admin("Peta", Role.ADMIN);
        System.out.println(user2);
        User user3 = new User();
        System.out.println(user3);
        Client user4 = new Client("Vita", Role.USER);
        System.out.println(user4);

        userServices.addUser(user1);
        userServices.addUser(user2);
        userServices.addUser(user3);
        userServices.addUser(user4);

        Ticket ticket1 = new Ticket("club", (short) 182, Sector.B, true, user2.getId(), TicketType.DAY);
        Ticket ticket2 = new Ticket("train", (short) 345, Sector.A, false, user4.getId(), TicketType.MONTH);
        Ticket ticket3 = new Ticket("Buss", (short) 678, Sector.C, false, user4.getId(), TicketType.YEAR);

        ticketServices.addTicket(ticket1);
        ticketServices.addTicket(ticket2);
        ticketServices.addTicket(ticket3);

        PhoneNumber phoneNumber1 = new PhoneNumber("+375291111111");
        PhoneNumber phoneNumber2 = new PhoneNumber("+375292222222");

        Email email = new Email("qqq@qwert.com");


        System.out.println(userServices.getUserById(user2.getId()));
        System.out.println(ticketServices.getTicketById(ticket2.getId()));

        user2.setName("Klara");
        ticket2.setStadiumSector(Sector.C);

        userServices.updateUser(user2);
        System.out.println(userServices.getUserById(user2.getId()));

        ticketServices.updateTicket(ticket2);
        System.out.println(ticketServices.getTicketById(ticket2.getId()));

        System.out.println("__________________");
        System.out.println("List tickets: ");
        List<Ticket> list = ticketServices.getTicketByUserId(user4.getId());
        list.forEach(System.out::println);


    }
}
