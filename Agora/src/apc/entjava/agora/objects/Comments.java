package apc.entjava.agora.objects;

public class Comments {
    private int comment_id;
    private String comment_text;
    private String comment_datePosted;
    private String comment_poster;

    public Comments(int comment_id, String comment_text, String comment_datePosted, String comment_poster) {
        this.comment_id = comment_id;
        this.comment_text = comment_text;
        this.comment_datePosted = comment_datePosted;
        this.comment_poster = comment_poster;
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

    public String getComment_poster() {
        return comment_poster;
    }

    public void setComment_poster(String comment_poster) {
        this.comment_poster = comment_poster;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comment_id=" + comment_id +
                ", comment_text='" + comment_text + '\'' +
                ", comment_datePosted='" + comment_datePosted + '\'' +
                ", comment_poster='" + comment_poster + '\'' +
                '}';
    }
}
