package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ProgettoLaboratorioB.main.Enums.EMOTION;

public class Emotions implements Serializable, Comparable<Emotions>
{
    private String emotion_description;

    private String IDSong;

    private String IDUser;

    private Map<EMOTION, Integer> EvaluateEmotion;

    public Emotions(String IDSong,String IDUser, String emotion_description, Map<EMOTION, Integer> EvaluateEmotion)
    {

        this.IDSong = IDSong;
        this.IDUser = IDUser;
        this.emotion_description = emotion_description;
        this.EvaluateEmotion = EvaluateEmotion;
    }

    public String GetEmotionDescription()
    {
        return this.emotion_description;
    }

    public String GetSong()
    {
        return this.IDSong;
    }

    public String GetUser()
    {
        return this.IDUser;
    }

    public Map<EMOTION, Integer> GetEvaluateEmotion()
    {
        return this.EvaluateEmotion;
    }

    public void SetEmotionDescription(String emotion_description)
    {
        this.emotion_description = emotion_description;
    }

    public void SetSong(String Song)
    {
        this.IDSong = Song;
    }

    public void SetUser(String User)
    {
        this.IDUser = User;
    }

    public void SetEvaluateEmotion(Map<EMOTION,Integer> EvaluateEmotion)
    {
        this.EvaluateEmotion = EvaluateEmotion;
    }

    @Override
    public int compareTo(Emotions emotions) {return 0;}
}
