package models;

public class TicketInTheater extends Ticket{

    @Override
    public void typeDeviceAccordingEventCode(Ticket ticket) {
        if (ticket.getEventCode() > 333 & ticket.getEventCode() < 667){
            System.out.println("We send ticket on your email and registration your number. " +
                               "Glade to see you in our theater");
        } else {
            System.out.println("You buy ticket on another event. Call administration!");
        }
    }
}
