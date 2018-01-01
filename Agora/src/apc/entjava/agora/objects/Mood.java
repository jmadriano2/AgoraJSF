package apc.entjava.agora.objects;

public class Mood {
    private String mood_happy;
    private String mood_sad;
    private String mood_angry;
    private String mood_disgusted;
    private String mood_fearful;

    public Mood(String mood_happy, String mood_sad, String mood_angry, String mood_disgusted, String mood_fearful) {
         this.mood_happy = mood_happy;
         this.mood_sad = mood_sad;
         this.mood_angry = mood_angry;
         this.mood_disgusted = mood_disgusted;
         this.mood_fearful = mood_fearful;
    }

    public String getMood_happy() {
        return mood_happy;
    }

    public void setMood_happy(String mood_happy) {
        this.mood_happy = mood_happy;
    }

    public String getMood_sad() {
        return mood_sad;
    }

    public void setMood_sad(String mood_sad) {
        this.mood_sad = mood_sad;
    }

    public String getMood_angry() {
        return mood_angry;
    }

    public void setMood_angry(String mood_angry) {
        this.mood_angry = mood_angry;
    }

    public String getMood_disgusted() {
        return mood_disgusted;
    }

    public void setMood_disgusted(String mood_disgusted) {
        this.mood_disgusted = mood_disgusted;
    }

    public String getMood_fearful() {
        return mood_fearful;
    }

    public void setMood_fearful(String mood_fearful) {
        this.mood_fearful = mood_fearful;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "mood_happy='" + mood_happy + '\'' +
                ", mood_sad='" + mood_sad + '\'' +
                ", mood_angry='" + mood_angry + '\'' +
                ", mood_disgusted='" + mood_disgusted + '\'' +
                ", mood_fearful='" + mood_fearful + '\'' +
                '}';
    }
}
