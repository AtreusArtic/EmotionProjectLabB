package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;
import java.util.List;

public class Playlist implements Serializable, Comparable<Playlist>
{
    private String playlist_name;

    private String userID;

    private String playlist_id;
    private List<Song> songs;

    public Playlist(String playlist_name, String playlist_owner,String playlist_id)
    {
        this.playlist_name = playlist_name;
        this.userID = playlist_owner;
        this.playlist_id = playlist_id;
    }
    public String GetPlaylistName()
    {
        return this.playlist_name;
    }

    public String GetPlaylistOwner()
    {
        return this.userID;
    }

    public String GetPlaylistID() {return this.playlist_id;}

    public List<Song> GetSongs()
    {
        return this.songs;
    }

    public void GetPlaylistName(String playlist_name)
    {
        this.playlist_name = playlist_name;
    }

    public void SetPlaylistOwner(String playlist_owner)
    {
        this.userID = playlist_owner;
    }

    public void SetPlaylistID(String playlist_id) {this.playlist_id = playlist_id;}

    public void SetSongs(List<Song> songs)
    {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlist_name='" + playlist_name + '\'' +
                ", userID='" + userID + '\'' +
                ", song list size= NOT IMPLEMENTED";
    }

    public static void PrintAllSongs(List<Song> songs)
    {
        for (Song song : songs)
        {
            System.out.println("SONG SAVED:");
            System.out.println(song.getTitle());
        }
    }

    @Override
    public int compareTo(Playlist playlist) {
        return 0;
    }
}
