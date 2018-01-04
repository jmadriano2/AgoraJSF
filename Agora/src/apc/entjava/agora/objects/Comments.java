package apc.entjava.agora.objects;

public class Comments {
    private int comment_index;
    private int comment_id;
    private String comment_text;
    private String comment_datePosted;
    private String comment_mood;
    private String comment_poster;
    private String comment_posterImg;

    public Comments(int comment_index, int comment_id, String comment_text, String comment_datePosted,
                    String comment_mood, String comment_poster, String comment_posterImg) {
        this.comment_index = comment_index;
        this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.comment_datePosted = comment_datePosted;
        this.comment_mood = comment_mood;
        this.comment_poster = comment_poster;
        this.comment_posterImg = comment_posterImg;
    }

    public int getComment_index() {
        return comment_index;
    }

    public void setComment_index(int comment_index) {
        this.comment_index = comment_index;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getComment_datePosted() {
        return comment_datePosted;
    }

    public void setComment_datePosted(String comment_datePosted) {
        this.comment_datePosted = comment_datePosted;
    }

    public String getComment_mood() {
        return comment_mood;
    }

    public void setComment_mood(String comment_mood) {
        this.comment_mood = comment_mood;
    }

    public String getComment_poster() {
        return comment_poster;
    }

    public void setComment_poster(String comment_poster) {
        this.comment_poster = comment_poster;
    }

    public String getComment_posterImg() {
        return comment_posterImg;
    }

    public void setComment_posterImg(String comment_posterImg) {
        this.comment_posterImg = comment_posterImg;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comment_index=" + comment_index +
                ", comment_id=" + comment_id +
                ", comment_text='" + comment_text + '\'' +
                ", comment_datePosted='" + comment_datePosted + '\'' +
                ", comment_mood='" + comment_mood + '\'' +
                ", comment_poster='" + comment_poster + '\'' +
                ", comment_posterImg='" + comment_posterImg + '\'' +
                '}';
    }
}
