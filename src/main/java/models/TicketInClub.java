package models;

public class TicketInClub extends Ticket{

    @Override
    public void typeDeviceAccordingEventCode(Ticket ticket) {
        if (ticket.getEventCode() < 333){
            System.out.println("We registration your number. " +
                    "Glade to see you in our club");
        } else {
            System.out.println("You buy ticket on another event. Call administration!");
        }
    }
}
