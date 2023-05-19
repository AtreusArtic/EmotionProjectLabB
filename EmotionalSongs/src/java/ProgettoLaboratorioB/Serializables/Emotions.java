package ProgettoLaboratorioB.Serializables;

public class Emotions implements java.io.Serializable
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

    public String getEmotionName()
    {
        return this.emotion_name;
    }

    public String getEmotionDescription()
    {
        return this.emotion_description;
    }

    public String getSong()
    {
        return this.Song;
    }

    public String getUser()
    {
        return this.User;
    }

    public void setEmotionName(String emotion_name)
    {
        this.emotion_name = emotion_name;
    }

    public void setEmotionDescription(String emotion_description)
    {
        this.emotion_description = emotion_description;
    }

    public void setSong(String Song)
    {
        this.Song = Song;
    }

    public void setUser(String User)
    {
        this.User = User;
    }



}
