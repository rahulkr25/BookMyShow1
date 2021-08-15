import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Helper.GuestUser;
import Helper.Movie;
import Helper.RegisteredUser;
import Helper.Shows;
import Helper.Theater;
import Helper.Ticket;
import Helper.TicketBookingThread;

public class BookMyShow {
    ArrayList<Theater> theaters;
    static HashMap<String,ArrayList<Shows>> movieMap;

    private void generateMovieMap(){
        for (Theater theater :theaters) {
            ArrayList<Shows> showArray = theater.getallshows();
            for(Shows show : showArray) {
                if (show != null) {
                    if (movieMap.containsKey(show.getmovie().getname())) {
                        movieMap.get(show.getmovie().getname()).add(show);
                    } else {
                        ArrayList<Shows> movieShowList = new ArrayList<>();
                        movieShowList.add(show);
                        movieMap.put(show.getmovie().getname(), movieShowList);
                    }
                }
            }
        }
    }
    public BookMyShow(ArrayList<Theater> theaters) {
        this.theaters = theaters;
        BookMyShow.movieMap = new HashMap<>();
        generateMovieMap();
        //System.out.println(movieMap);
    }
    public static ArrayList<Shows> searchShows(String movieName){
        if (movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        else
            return null;
    }

    public static void main(String[] args) {
        /* --------Data generation code ----START ----------------- */

        // Creating Guest User --> Piyush
        GuestUser piyush = new GuestUser("Piyush");

        // Creating Registered User --> Ayush
        RegisteredUser ayush = new RegisteredUser("Ayush");

        // Creating Registered User --> Saurabh
        RegisteredUser saurabh = new RegisteredUser("Saurabh");

        // Creating Movie object --> Iron Man
        Movie ironMan = new Movie("Iron Man", "Language.ENGLISH","Genre.ACTION");

        // Creating Movie object --> Avengers: End Game
        Movie avengers = new Movie("Avengers: End Game", "Language.ENGLISH","Genre.ACTION");

        // Creating Movie object --> The Walk To Remember
        Movie walkToRemember = new Movie("The Walk To Remember", "Language.ENGLISH","Genre.ROMANCE");

        // Creating Movie object --> HouseFull2
        Movie housefull = new Movie("HouseFull 2", "Language.HINDI","Genre.COMEDY");

        // Creating Theater --> PVR @ GIP Noida with capacity 30
        Theater pvr_gip = new Theater("PVR","GIP Noida",30);

        // Creating Another Theater --> BIG Cinema @ Noida Sector 137 with capacity 40
        Theater big_cinema = new Theater("Big Cinema","Sector 137 Noida",40);




        // Creating four shows for movies
        Shows show1=null, show2=null, show3=null, show4=null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

        try {
            // Creating Show for Movie Iron Man on 7 Jun 2020 @ 9:00 AM in PVR
            String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            Date date = formatter.parse(dateInString);
            show1 = new Shows(ironMan,pvr_gip,date);

            // Creating Show for Movie HOUSEFULL on 7 Jun 2020 @ 12:00 PM in PVR
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show2 = new Shows(housefull,pvr_gip,date);

            // Creating Show for Movie WALK TO REMEMBER on 7 Jun 2020 @ 09:00 AM in BIG-CINEMA
            dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            date = formatter.parse(dateInString);
            show3 = new Shows(walkToRemember,big_cinema,date);

            // Creating Show for Movie WALK TO REMEMBER on 7 Jun 2020 @ 12:00 PM in BIG-CINEMA
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show4 = new Shows(walkToRemember,big_cinema,date);

            } catch (ParseException e) {
                    e.printStackTrace();
        }

        /* --------Data generation code ---- END ----------------- */

        // Now We have two theaters with their shows, lets add these theaters to our Book My Show app
        ArrayList<Theater> theaterArrayList= new ArrayList<>();
        theaterArrayList.add(pvr_gip);
        theaterArrayList.add(big_cinema);
        BookMyShow bookMyShow = new BookMyShow(theaterArrayList);

        // Searching Book My Show for all the shows of movie WALK TO REMEMBER
        ArrayList<Shows> searchedShow = BookMyShow.searchShows("The Walk To Remember");
        for(Shows shows:searchedShow)
        {
            System.out.println(shows.getTheater().getname()+" "+shows.getmovie().getlanguage()+" "+shows.getavailableseats());
        }

        // Above code returns two shows of WALK TO REMEMBER
        /*
        searchedShow --> [Show{id=3, showTime=Sun Jun 07 09:00:00 IST 2020, 
        movie=The Walk To Remember, theater=Big Cinema, availableSeats=40}, 
        Show{id=4, showTime=Sun Jun 07 12:00:00 IST 2020, movie=The Walk To Remember, 
        theater=Big Cinema, availableSeats=40}]
        */
        // Now suppose AYUSH and SAURABH both wants to book 10 tickets each for first show
        // Then Book My show will create two Ticket Booking threads for their requests

       Shows bookingShow = searchedShow.get(0);

        // Ticket Booking Thread for the request made by AYUSH for 10 Seats
       TicketBookingThread t1 = new TicketBookingThread(bookingShow,ayush,10);

        // Ticket Booking Thread for the request made by SAURABH for 10 Seats
        TicketBookingThread t2 = new TicketBookingThread(bookingShow,saurabh,10);

       // Start both the Ticket Booking Threads for execution
        t1.start();
        t2.start();

        // Waiting till both the thread completes the execution
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After execution t1 will carry AYUSH ticket and t2 will carry SAURABH ticket
        Ticket ayush_ticket = t1.getTicket();
        Ticket saurabh_ticket = t2.getTicket();

        // Printing their tickets
        System.out.println(t1.getTicket());
        System.out.println(t2.getTicket());

        // Now, 20 seats are booked for this show and 20 seats are available,
        // Suppose AYUSH wants another 15 seats and SAURABH wants another 10 seats to be booked

        // Ticket Booking Thread for the request made by AYUSH for another 15 Seats
        TicketBookingThread t3 = new TicketBookingThread(bookingShow,ayush,15);

        // Ticket Booking Thread for the request made by SAURABH for another 10 Seats
        TicketBookingThread t4 = new TicketBookingThread(bookingShow,saurabh,10);

        // Start both the Ticket Booking Threads for execution
        t3.start();
        t4.start();

        // Waiting till both the thread completes the execution
        try {

            t4.join();
            t3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // After execution t3 will carry AYUSH ticket and t4 will carry SAURABH ticket
        Ticket ayushNewTicket = t3.getTicket();
        Ticket saurabhNewTicket = t4.getTicket();

        System.out.println(ayushNewTicket);
        System.out.println(saurabhNewTicket);

        /* Running this program several times, we will notice that 
        if t3 enters the bookTicket function first, 
        then ticket is allocated to Ayush, 
        otherwise ticket is allocated to Saurabh.
         */

    }
}

/*
public class BookMyShow {
     static ArrayList<Theater> theaters;
    static HashMap<String,ArrayList<Shows>> movieMap;
    private void generateMovieMap()
    {
        for(Theater theater:theaters)
        {
            ArrayList<Shows>showArray=theater.getallshows();
            for(Shows shows:showArray)
            {
                if(shows!=null){
                String movieName=shows.getmovie().getname();
                if(movieMap.containsKey(movieName))
                {
                    movieMap.get(movieName).add(shows);
                }
                }
                else{
                    ArrayList<Shows> movieShowList = new ArrayList<>();
                    movieShowList.add(shows);
                    movieMap.put(shows.getmovie().getname(), movieShowList);
                }
            }

        }
    }
    public BookMyShow() {
        BookMyShow.theaters = new ArrayList<>();
        BookMyShow.movieMap = new HashMap<>();
        generateMovieMap();
        System.out.println(movieMap);
    }
    public static ArrayList<Shows> searchShows(String movieName){
        if (movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        else
            return null;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        GuestUser piyush=new GuestUser("Piyush");
        RegisteredUser rahul=new RegisteredUser("Rahul");
        Movie ironman=new Movie("Iron Man","ENGLISH","ACTION");
        Movie avengers = new Movie("Avengers: End Game", "ENGLISH","ACTION");
          // Creating Movie object --> The Walk To Remember
          Movie walkToRemember = new Movie("The Walk To Remember", "ENGLISH","ROMANCE");

          // Creating Movie object --> HouseFull2
          Movie housefull = new Movie("HouseFull 2", "HINDI","COMEDY");

        Theater pvr_gip = new Theater("PVR","GIP Noida",30);
        Theater big_cinema = new Theater("Big Cinema","Sector 137 Noida",40);
        Shows show1=null, show2=null, show3=null, show4=null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

        try {
            // Creating Show for Movie Iron Man on 7 Jun 2020 @ 9:00 AM in PVR
            String dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            Date date = formatter.parse(dateInString);
            show1 = new Shows(ironman,pvr_gip,date);

            // Creating Show for Movie HOUSEFULL on 7 Jun 2020 @ 12:00 PM in PVR
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show2 = new Shows(housefull,pvr_gip,date);

            // Creating Show for Movie WALK TO REMEMBER on 7 Jun 2020 @ 09:00 AM in BIG-CINEMA
            dateInString = "Friday, Jun 7, 2020 09:00:00 AM";
            date = formatter.parse(dateInString);
            show3 = new Shows(walkToRemember,big_cinema,date);

            // Creating Show for Movie WALK TO REMEMBER on 7 Jun 2020 @ 12:00 PM in BIG-CINEMA
            dateInString = "Friday, Jun 7, 2020 12:00:00 PM";
            date = formatter.parse(dateInString);
            show4 = new Shows(walkToRemember,big_cinema,date);

            } catch (ParseException e) {
                    e.printStackTrace();
        }
        ArrayList<Theater> theaterArrayList= new ArrayList<>();
        theaterArrayList.add(pvr_gip);
        theaterArrayList.add(big_cinema);
        //BookMyShow bookMyShow = new BookMyShow(theaterArrayList);
         theaters.add(pvr_gip);
         theaters.add(big_cinema);
         ArrayList<Shows> searchedShow = BookMyShow.searchShows("The Walk To Remember");
         System.out.println(searchedShow);
         
    }
}
*/