package Helper;

import java.util.Date;
 

public class Shows {
   //id,Movie name,theater,availableseat,showtime;
   private static int idcounter=0;
   private int id;
   private Movie movie;
   private Theater theater;
   private int availableseat;
   private Date showtime;
   public Shows(Movie movie,Theater theater,Date showtime)
   {
       id=idcounter++;
       this.theater=theater;
       this.availableseat=theater.getcapacity();
       this.movie=movie;
       this.showtime=showtime;
       theater.getallshows().add(this);
   }
   public Date getshowtime()
   {
       return showtime;
   }
   public int getavailableseats()
   {
       return availableseat;
   }
   public void setTheater(Theater theater)
   {
       this.theater=theater;
   }
   public Movie getmovie()
   {
       return movie;
   }
   public void setavailableseats(int availableseat)
   {
       this.availableseat=availableseat;
   }
   public  Theater getTheater()
   {
       return theater;
   }
   public Ticket bookticket(RegisteredUser user,int seats)
   {
       if(availableseat>=seats&&seats>0){
       Ticket ticket=new Ticket();
       availableseat-=seats;
       ticket.setOwner(user.getName());
       ticket.setNumberOfSeats(seats);
       ticket.setBookedShow(this);
       ticket.setBookingTime(new java.util.Date());
       System.out.println("Successfully booked");
       user.bookinghistory.add(ticket);
       return ticket;
       }
       else{
        System.out.println("Seats not Available");
        return null;
       }
   }

  
}
