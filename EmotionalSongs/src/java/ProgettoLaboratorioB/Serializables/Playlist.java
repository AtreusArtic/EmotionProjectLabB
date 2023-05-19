package ProgettoLaboratorioB.Serializables;

public class Playlist implements java.io.Serializable
{
    private String playlist_name;

    private User playlist_owner;

    private Song[] songs;

    public Playlist(String playlist_name, User playlist_owner, Song[] songs)
    {
        this.playlist_name = playlist_name;
        this.playlist_owner = playlist_owner;
        this.songs = songs;
    }

    public String getPlaylistName()
    {
        return this.playlist_name;
    }

    public User getPlaylistOwner()
    {
        return this.playlist_owner;
    }

    public Song[] getSongs()
    {
        return this.songs;
    }

    public void setPlaylistName(String playlist_name)
    {
        this.playlist_name = playlist_name;
    }

    public void setPlaylistOwner(User playlist_owner)
    {
        this.playlist_owner = playlist_owner;
    }

    public void setSongs(Song[] songs)
    {
        this.songs = songs;
    }


}
