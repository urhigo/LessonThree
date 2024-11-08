package services;

import models.Sector;
import models.Ticket;
import org.springframework.transaction.annotation.Transactional;
import repository.DAOTicket;

import java.time.LocalDateTime;
import java.util.List;

public class TicketService {

    private final DAOTicket daoTicket;

    public TicketService(DAOTicket daoTicket) {
        this.daoTicket = daoTicket;
    }

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



    /*This method get you ability edit information about sector*/
    public void editSector(Ticket ticket, Sector newSector){
        ticket.setStadiumSector(newSector);
    }

    public void lookInformationAboutAllTicket(List<Ticket> listTickets){
        listTickets.forEach(System.out::println);
    }


    @Transactional
    public void addTicketInDB(Ticket ticket){
        daoTicket.addTicket(ticket);
    }

    public Ticket getTicketFromDBById(String id){
        return daoTicket.getTicketById(id);
    }

    public List<Ticket> getListTicketsUser(String id){
        return daoTicket.getTicketByUserId(id);
    }

    @Transactional
    public void updateTicket(Ticket ticket){
        daoTicket.updateTicket(ticket);
    }

    @Transactional
    public void deleteTicket(String id){
        daoTicket.deleteTicket(id);
    }
}
