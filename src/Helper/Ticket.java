package Helper;

import java.util.Date;

public class Ticket {
    //name,show,seats,bookingtime,id
    private static int idcounter=0;
    private String name;
    private int seats;
    private Shows shows;
    private Date bookingtime;
    private int id;
    public Ticket()
    {
       id=idcounter++;
    }
    public String getTicketInfo(){
        return null;
    }
    public int cancelTicket(){
        return 0;
    }

    public String getOwner() {
        return name;
    }

    public void setOwner(String owner) {
        this.name = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingTime() {
        return bookingtime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingtime = bookingTime;
    }

    public int getNumberOfSeats() {
        return seats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.seats = numberOfSeats;
    }
 

    public Shows getBookedShow() {
        return shows;
    }

    public void setBookedShow(Shows bookedShow) {
        this.shows = bookedShow;
    }

}
