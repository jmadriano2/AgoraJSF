package apc.entjava.agora.objects;

public class User {
    private int user_id;
    private String user_name;
    private String user_fname;
    private String user_lname;
    private String user_dateJoined;
    private String user_email;
    private String user_nickname;
    private String user_imgpath;

    public User(int user_id, String user_name, String user_fname, String user_lname, String user_dateJoined,
                String user_email, String user_nickname, String user_imgpath) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_dateJoined = user_dateJoined;
        this.user_email = user_email;
        this.user_nickname = user_nickname;
        this.user_imgpath = user_imgpath;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public String getUser_dateJoined() {
        return user_dateJoined;
    }

    public void setUser_dateJoined(String user_dateJoined) {
        this.user_dateJoined = user_dateJoined;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_imgpath() {
        return user_imgpath;
    }

    public void setUser_imgpath(String user_imgpath) {
        this.user_imgpath = user_imgpath;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", user_lname='" + user_lname + '\'' +
                ", user_dateJoined='" + user_dateJoined + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_imgpath='" + user_imgpath + '\'' +
                '}';
    }
}
