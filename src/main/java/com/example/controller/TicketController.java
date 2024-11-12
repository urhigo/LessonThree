package com.example.controller;

import com.example.models.Ticket;
import com.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public void addTicket(@RequestBody Ticket ticket){
        ticketService.addTicket(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable(name = "id") long id){
        return ticketService.getTicketById(id);
    }

}
