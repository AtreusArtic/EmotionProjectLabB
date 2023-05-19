package ProgettoLaboratorioB.Serializables;

public class Song implements java.io.Serializable{

    static final long serialVersionUID = 1L;

    private String title;

    private String artist;

    private int year;

    private String ID;


    public Song(String title, String artist, int year, String ID){
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.ID = ID;
    }

    public Song()
    {

    }

    public String getTitle(){
        return this.title;
    }

    public String getArtist(){
        return this.artist;
    }

    public int getYear(){
        return this.year;
    }

    public String getID(){
        return this.ID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public void setYear(int year){
        this.year = year;
    }
}
