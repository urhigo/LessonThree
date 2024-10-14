package models;

import Interface.Printable;
import services.BaseIdGeneratingEntity;
import services.TicketService;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends BaseIdGeneratingEntity implements Printable {


    private String concertHall;                                                         // Name concert hall. Max length 10 symbols.
    private short eventCode;                                                            // Unique code event. You get it code when buy ticket.
    private final LocalDateTime timeCreateTicket = LocalDateTime.now();                 // Local time when you buy your ticket.
    private LocalDateTime dateEvent;                                                    // Date when will be event.
    private Sector stadiumSector;                                                        // Sector where you will be on event.
    private boolean promo;                                                              // This is the event kind of marketing promotion or not.
    private float maxBackpackWeight;                                                    // Max weight backpack on event according cod event.


    public Ticket() {
    }

    public Ticket(String concertHall, short eventCode, Sector stadiumSector, boolean promo) {
        if (controlEventCode(eventCode) & controlLengthNamePlace(concertHall) & controlInformationAboutSector(stadiumSector)) {
            this.concertHall = concertHall;
            this.eventCode = eventCode;
            this.stadiumSector = stadiumSector;
            this.promo = promo;
            this.maxBackpackWeight = new TicketService().maxWeightAccordingEventCode(eventCode);
            this.dateEvent = new TicketService().dateEvent(eventCode);
        } else {
            throw new IllegalArgumentException("Invalid ticket information");
        }
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

    public void typeDeviceAccordingEventCode(Ticket ticket) {
        System.out.println("If your code event <333 you visit club and you can use only number of your phone\n" +
                           "If your code event >333 and <667 you visit theater. We send ticket on your email or you" +
                "can use number of your phone\n" +
                           "If your code event  >=667 you visit cinema. We send ticket on your email or you" +
                "can use number of your phone\n"
        );
    }

    @Override
    public void printInformationAboutObject(Object object) {
        System.out.println(object);
    }
}