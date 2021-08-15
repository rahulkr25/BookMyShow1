package Helper;
public class TicketBookingThread extends Thread {
    private Shows show;
    private RegisteredUser user;
    private int numberOfSeats;
    private Ticket ticket;

    public TicketBookingThread(Shows show, RegisteredUser user, int numberOfSeats) {
        this.show = show;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public void run() {
        this.ticket = show.bookticket(user,numberOfSeats);
    }

    public Ticket getTicket() {
        return ticket;
    }
}
