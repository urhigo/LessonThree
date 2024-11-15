package com.example.services;

import com.example.models.Ticket;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public TicketService() {

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

    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }

    public Ticket getTicketById(long id){
        if (ticketRepository.findTicketById(id) != null){
            return ticketRepository.findTicketById(id);
        } else {
            throw new NullPointerException("No found ticket with id: " + id);
        }
    }
}
