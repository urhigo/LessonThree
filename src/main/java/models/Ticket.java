package models;

import Interface.Printable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import services.TicketService;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private String concertHall;                                                         // Name concert hall. Max length 10 symbols.
    @Transient
    private short eventCode;                                                            // Unique code event. You get it code when buy ticket.
    @Column(name = "creation_date")
    private LocalDateTime timeCreateTicket = LocalDateTime.now();                 // Local time when you buy your ticket.
    @Transient
    private LocalDateTime dateEvent;                                                    // Date when will be event.
    @Transient
    private Sector stadiumSector;                                                        // Sector where you will be on event.
    @Transient
    private boolean promo;                                                              // This is the event kind of marketing promotion or not.
    @Transient
    private float maxBackpackWeight;                                                    // Max weight backpack on event according cod event.
    @Column(name = "ticket_type")
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket(String concertHall, short eventCode, Sector stadiumSector, boolean promo, TicketType ticketType) {
        if (controlEventCode(eventCode) & controlLengthNamePlace(concertHall) & controlInformationAboutSector(stadiumSector)) {
            this.concertHall = concertHall;
            this.eventCode = eventCode;
            this.stadiumSector = stadiumSector;
            this.promo = promo;
            this.maxBackpackWeight = new TicketService().maxWeightAccordingEventCode(eventCode);
            this.dateEvent = new TicketService().dateEvent(eventCode);
            this.ticketType = ticketType;
        } else {
            throw new IllegalArgumentException("Invalid ticket information");
        }
    }

    public Ticket(String concertHall, short eventCode, Sector stadiumSector, boolean promo, String userId, TicketType ticketType) {
        if (controlEventCode(eventCode) & controlLengthNamePlace(concertHall) & controlInformationAboutSector(stadiumSector)) {
            this.concertHall = concertHall;
            this.eventCode = eventCode;
            this.stadiumSector = stadiumSector;
            this.promo = promo;
            this.maxBackpackWeight = new TicketService().maxWeightAccordingEventCode(eventCode);
            this.dateEvent = new TicketService().dateEvent(eventCode);
            this.userId = userId;
            this.ticketType = ticketType;
        } else {
            throw new IllegalArgumentException("Invalid ticket information");
        }
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getTimeCreateTicket() {
        return timeCreateTicket;
    }

    public float getMaxBackpackWeight() {
        return maxBackpackWeight;
    }

    public LocalDateTime getDateEvent() {
        return dateEvent;
    }

    public boolean isPromo() {
        return promo;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public short getEventCode() {
        return eventCode;
    }

    public Sector getStadiumSector() {
        return stadiumSector;
    }

    public void setStadiumSector(Sector stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public void setDateEvent(LocalDateTime dateEvent) {
        this.dateEvent = dateEvent;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setTimeCreateTicket(LocalDateTime timeCreateTicket) {
        this.timeCreateTicket = timeCreateTicket;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Ticket ticket = (Ticket) object;
        return eventCode == ticket.eventCode && promo == ticket.promo && Float.compare(maxBackpackWeight, ticket.maxBackpackWeight) == 0 && Objects.equals(concertHall, ticket.concertHall) && Objects.equals(timeCreateTicket, ticket.timeCreateTicket) && Objects.equals(dateEvent, ticket.dateEvent) && stadiumSector == ticket.stadiumSector;
    }

    @Override
    public int hashCode() {
        return Objects.hash(concertHall, eventCode, timeCreateTicket, dateEvent, stadiumSector, promo, maxBackpackWeight);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", timeCreateTicket=" + timeCreateTicket +
                ", dateEvent=" + dateEvent +
                ", stadiumSector=" + stadiumSector +
                ", promo=" + promo +
                ", maxBackpackWeight=" + maxBackpackWeight +
                '}';
    }

    public boolean controlTicketId(short id) {
        if (id > 9999) {
            System.out.println("We don't have tickets");
            return false;
        }
        return true;
    }

    private boolean controlEventCode(short eventCode) {
        if (eventCode > 999 || eventCode < 1) {
            System.out.println("We don't have event with this code");
            return false;
        }
        return true;
    }

    private boolean controlInformationAboutSector(Sector sector) {
        if (sector != Sector.A & sector != Sector.B & sector != Sector.C) {
            System.out.println("We don't have this sector");
            return false;
        }
        return true;
    }


    private boolean controlLengthNamePlace(String namePlace) {
        if (namePlace.length() > 10) {
            System.out.println("Name place have more 10 symbols");
            return false;
        }
        return true;
    }
    public void shared (String phoneNumber){
        System.out.println("Ticket shared by phone number: " + phoneNumber);
    }

    public void shared (String phoneNumber, String email){
        System.out.println("Ticket shared by phone number: " + phoneNumber +
                "\nTicked shared by email: " + email);
    }

    @Override
    public void printInformationAboutObject(Object object) {
        System.out.println(object);
    }
}