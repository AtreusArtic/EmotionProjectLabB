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

    public Song() {}

    public String GetTitle(){
        return this.title;
    }

    public String GetArtist(){
        return this.artist;
    }

    public int getYear(){
        return this.year;
    }

    public String GetID(){
        return this.ID;
    }

    public void SetTitle(String title){
        this.title = title;
    }

    public void SetArtist(String artist){
        this.artist = artist;
    }

    public void SetYear(int year){
        this.year = year;
    }
}
