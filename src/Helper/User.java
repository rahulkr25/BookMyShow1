package Helper;

public abstract class User {
    private String name;
    private static int idcounter=0;
    private int id;
    
    public User(String name) {
        idcounter += 1;
        this.id = idcounter;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
