package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;

public class Emotions implements Serializable, Comparable<Emotions>
{
    private String emotion_name;

    private String emotion_description;

    private String Song;

    private String User;

    public Emotions(String emotion_name, String emotion_description, String Song, String User)
    {
        this.emotion_name = emotion_name;
        this.emotion_description = emotion_description;
        this.Song = Song;
        this.User = User;
    }

    public String GetEmotionName()
    {
        return this.emotion_name;
    }

    public String GetEmotionDescription()
    {
        return this.emotion_description;
    }

    public String GetSong()
    {
        return this.Song;
    }

    public String GetUser()
    {
        return this.User;
    }

    public void GetEmotionName(String emotion_name)
    {
        this.emotion_name = emotion_name;
    }

    public void GetEmotionDescription(String emotion_description)
    {
        this.emotion_description = emotion_description;
    }

    public void GetSong(String Song)
    {
        this.Song = Song;
    }

    public void GetUser(String User)
    {
        this.User = User;
    }



    @Override
    public int compareTo(Emotions emotions) {return 0;}
}
