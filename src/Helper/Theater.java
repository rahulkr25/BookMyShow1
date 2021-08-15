package Helper;

import java.util.ArrayList;

public class Theater {
    //id,name,location,capacity,arraylist<shows>
    private static int idcounter=0;
    private String name,location;
    private int capacity,id;
    ArrayList<Shows>shows;
    public Theater(String name,String location,int capacity)
    {
        this.id=idcounter++;
        this.name=name;
        this.capacity=capacity;
        this.location=location;
        shows=new ArrayList<>();
    }
    public ArrayList<Shows> getallshows()
    {
        return shows;
    }
    public String getname()
    {
        return name;
    }
    public int getcapacity()
    {
        return capacity;
    }
    public String getlocation()
    {
        return location;
    }

}
