package ProgettoLaboratorioB.Serializables;

import java.util.List;

public class Playlist implements java.io.Serializable
{
    private String playlist_name;

    private String userID;

    private List<Song> songs;

    public Playlist(String playlist_name, String playlist_owner)
    {
        this.playlist_name = playlist_name;
        this.userID = playlist_owner;
    }

    public String GetPlaylistName()
    {
        return this.playlist_name;
    }

    public String GetPlaylistOwner()
    {
        return this.userID;
    }

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

    public void SetSongs(List<Song> songs)
    {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlist_name='" + playlist_name + '\'' +
                ", userID='" + userID + '\'' +
                ", song list size='" + songs.size() + '\'';
    }

    public static void PrintAllSongs(List<Song> songs)
    {
        for (Song song : songs)
        {
            System.out.println("SONG SAVED:");
            System.out.println(song.GetTitle());
        }
    }
}
