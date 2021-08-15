package Helper;

import java.util.ArrayList;

public class RegisteredUser extends User{
  public ArrayList<Ticket>bookinghistory;
    public RegisteredUser(String name) {
        super(name);
        bookinghistory=new ArrayList<>();
    }
    
    
}
