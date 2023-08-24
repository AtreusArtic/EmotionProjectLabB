package ProgettoLaboratorioB.Serializables;

import ProgettoLaboratorioB.main.Enums.EMOTION;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class Emotions implements Serializable, Comparable<Emotions> {
    private String emotion_description;

    private String IDSong;

    private String IDUser;

    private Map<EMOTION, Integer> EvaluateEmotion;

    public Emotions(String IDSong, String IDUser, String emotion_description, Map<EMOTION, Integer> EvaluateEmotion) {

        this.IDSong = IDSong;
        this.IDUser = IDUser;
        this.emotion_description = emotion_description;
        this.EvaluateEmotion = EvaluateEmotion;
    }

    public String GetEmotionDescription() {
        return this.emotion_description;
    }

    public String GetSong() {
        return this.IDSong;
    }

    public String GetUser() {
        return this.IDUser;
    }

    public Map<EMOTION, Integer> GetEvaluateEmotion() {
        return this.EvaluateEmotion;
    }

    public void SetEmotionDescription(String emotion_description) {
        this.emotion_description = emotion_description;
    }

    public void SetSong(String Song) {
        this.IDSong = Song;
    }

    public void SetUser(String User) {
        this.IDUser = User;
    }

    public void SetEvaluateEmotion(Map<EMOTION, Integer> EvaluateEmotion) {
        this.EvaluateEmotion = EvaluateEmotion;
    }


    @Override
    public String toString() {
        return "Emotions{" +
                "emotion_description='" + emotion_description + '\'' +
                ", IDSong='" + IDSong + '\'' +
                ", IDUser='" + IDUser + '\'' +
                ", EvaluateEmotion=" + EvaluateEmotion +
                '}';
    }


    /**
     * This method is used to print the summary of a song. If the song searched doesn't have any registration, the system return an error.
     * @param song the song to be printed the emotion.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if the file is not found.
     */
    public static String[] ShowSummaryEmot(Song song, List<Emotions> emotions_list) throws ClassNotFoundException, IOException{

        float[] avgValues = new float [9];

        if(emotions_list == null){
            System.out.println("@SYSTEM: '" + song.GetTitle() + "' has not any emotion registered.\n");
            return null;
        }

        float count = 0f;
        for (int i = 0; i < emotions_list.size(); i++) {
            Integer[] int_values = emotions_list.get(i).GetEvaluateEmotion().values().toArray(new Integer[0]);
            float[] temp = new float[int_values.length];
            if(temp != null){
                count++;
                avgValues = SumArray(avgValues, temp);
            }
            if(avgValues != null){
                for (int j = 0; j < avgValues.length; j++) {
                    avgValues[j] = avgValues[j] / count;
                }
            }
        }

        System.out.println("\n > Total emotion registered about " + song.GetTitle() + " song is: " + (int)count);

        //round the average values:
        String[] roundValues = new String[avgValues.length];
        roundValues = NumberRounder(avgValues);
        return roundValues;
    }


    /**
     * This function is used to calculate the average of emotion values of a song.
     * @param arr the array of values to be summed.
     * @param inpArr the array of values to be summed.
     * @return the array of summed values.
     */
    private static float[] SumArray(float [] arr, float [] inpArr){
        for (int i = 0; i < inpArr.length; i++) {
            for (int j = i; j < arr.length;) {
                arr[j] = arr[j] + inpArr[i];
                break;
            }
        }
        return arr;
    }

    /**
     * This function is used to take as input param a float array, to round values in it.
     * @param numbers input float array to be rounded.
     * @return a String array with rounded values to be print.
     */
    private static String[] NumberRounder(float [] numbers){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String[] fNumbers = new String[numbers.length];

        for (int i = 0; i < fNumbers.length; i++) {
            fNumbers[i] = formatter.format(numbers[i]);
        }

        return fNumbers;
    }
    @Override
    public int compareTo(Emotions emotions) {
        return 0;
    }
}
