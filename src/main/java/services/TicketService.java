package services;

import models.Sector;
import models.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


public class TicketService {

    /*Give information about max weight backpack according event code*/
    public float maxWeightAccordingEventCode(short codeEvent) {

        if (codeEvent == 0) {
            return 0.000f;
        }
        if (codeEvent < 333) {
            return 3.500f;
        } else if (codeEvent <= 665) {
            return 4.200f;
        } else {
            return 5.300f;
        }
    }

    /*Give information when will be event according event code*/
    public LocalDateTime dateEvent(short codeEvent) {
        if (codeEvent == 0) {
            return LocalDateTime.now();
        }
        if (codeEvent < 333) {
            return LocalDateTime.of(2024, 3, 15, 18, 20);
        } else if (codeEvent <= 665) {
            return LocalDateTime.of(2024, 9, 27, 16, 30);
        } else {
            return LocalDateTime.of(2024, 12, 5, 20, 30);
        }
    }

    public List<Ticket> ticketsStorage(){
        Ticket ticket1 = new Ticket ("Palace", (short) 123, Sector.B, true);
        Ticket ticket2 = new Ticket ("Hall", (short) 223, Sector.A, true);
        Ticket ticket3 = new Ticket ("Brazil", (short) 111, Sector.C, true);
        Ticket ticket4 = new Ticket ("Stadium", (short) 234, Sector.C, true);
        Ticket ticket5 = new Ticket ("Theater", (short) 456, Sector.A, true);
        Ticket ticket6 = new Ticket ("Theater", (short) 276, Sector.B, true);
        Ticket ticket7 = new Ticket ("Hall", (short) 987, Sector.B, true);
        Ticket ticket8 = new Ticket ("Brazil", (short) 345, Sector.A, true);
        Ticket ticket9 = new Ticket ("Club", (short) 222, Sector.A, true);
        Ticket ticket10 = new Ticket ("Club", (short) 333, Sector.B, true);

        return Stream.of(ticket1, ticket2, ticket3, ticket4, ticket5,
                ticket6, ticket7, ticket8, ticket9, ticket10).toList();
    }

    /*This method give you ability find ticket by id*/
    public void getInformationAboutTicketById(short id, List<Ticket> listTickets){
        listTickets.stream().filter(ticket -> ticket.getId() == id).findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("We don't have ticket" +
                        " with this id: " + id));
    }


    /*This method get you ability edit information about sector*/
    public void editSector(Ticket ticket, Sector newSector){
        ticket.setStadiumSector(newSector);
    }

    public void lookInformationAboutAllTicket(List<Ticket> listTickets){
        listTickets.forEach(System.out::println);
    }

}
