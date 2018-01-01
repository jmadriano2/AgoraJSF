package apc.entjava.agora.objects;

public class Mood {
    private int mood_happy;
    private int mood_sad;
    private int mood_angry;
    private int mood_disgusted;
    private int mood_fearful;

    public Mood(int mood_happy, int mood_sad, int mood_angry, int mood_disgusted, int mood_fearful) {
        this.mood_happy = mood_happy;
        this.mood_sad = mood_sad;
        this.mood_angry = mood_angry;
        this.mood_disgusted = mood_disgusted;
        this.mood_fearful = mood_fearful;
    }

    public int getMood_happy() {
        return mood_happy;
    }

    public void setMood_happy(int mood_happy) {
        this.mood_happy = mood_happy;
    }

    public int getMood_sad() {
        return mood_sad;
    }

    public void setMood_sad(int mood_sad) {
        this.mood_sad = mood_sad;
    }

    public int getMood_angry() {
        return mood_angry;
    }

    public void setMood_angry(int mood_angry) {
        this.mood_angry = mood_angry;
    }

    public int getMood_disgusted() {
        return mood_disgusted;
    }

    public void setMood_disgusted(int mood_disgusted) {
        this.mood_disgusted = mood_disgusted;
    }

    public int getMood_fearful() {
        return mood_fearful;
    }

    public void setMood_fearful(int mood_fearful) {
        this.mood_fearful = mood_fearful;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "mood_happy=" + mood_happy +
                ", mood_sad=" + mood_sad +
                ", mood_angry=" + mood_angry +
                ", mood_disgusted=" + mood_disgusted +
                ", mood_fearful=" + mood_fearful +
                '}';
    }
}
