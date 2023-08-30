package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;

public class Song implements Serializable, Comparable<Song> {

    static final long serialVersionUID = 1L;
    private String title;
    private String artist;
    private int year;
    private String ID;


    public Song(int year, String id, String artist, String title){
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.ID = id;
    }

    public Song() {}

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

    public void SetTitle(String title){
        this.title = title;
    }

    public void SetArtist(String artist){
        this.artist = artist;
    }

    public void SetYear(int year){
        this.year = year;
    }


    @Override
    public String toString() {
        return "Song{" +
                "title='" + getTitle() + '\'' +
                ", artist='" + getArtist() + '\'' +
                ", year=" + getYear() +
                ", ID='" + getID() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Song song) { return 0;}

}
