package Helper;
public class Movie {
    //id,name,genre,language,
    private String name;
    private String genre;
    private String language;
    public Movie(String name,String genre,String language)
    {
        this.name=name;
        this.genre=genre;
        this.language=language;
    }
    public String getname()
    {
        return name;
    }
    public String getgenre()
    {
        return genre;
    }
    public String getlanguage()
    {
        return language;
    }


}
