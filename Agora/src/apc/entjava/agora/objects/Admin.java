package apc.entjava.agora.objects;

public class Admin {
    private int admin_id;
    private String admin_fullname;
    private String admin_name;
    private String admin_dateJoined;
    private String admin_email;
    private String admin_nickname;
    private String admin_imgPath;
    private int city_fk;

    public Admin(int admin_id, String admin_fullname, String admin_name, String admin_dateJoined, String admin_email,
                 String admin_nickname, String admin_imgPath, int city_fk) {
        this.admin_id = admin_id;
        this.admin_fullname = admin_fullname;
        this.admin_name = admin_name;
        this.admin_dateJoined = admin_dateJoined;
        this.admin_email = admin_email;
        this.admin_nickname = admin_nickname;
        this.admin_imgPath = admin_imgPath;
        this.city_fk = city_fk;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_fullname() {
        return admin_fullname;
    }

    public void setAdmin_fullname(String admin_fullname) {
        this.admin_fullname = admin_fullname;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_dateJoined() {
        return admin_dateJoined;
    }

    public void setAdmin_dateJoined(String admin_dateJoined) {
        this.admin_dateJoined = admin_dateJoined;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public void setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
    }

    public String getAdmin_imgPath() {
        return admin_imgPath;
    }

    public void setAdmin_imgPath(String admin_imgPath) {
        this.admin_imgPath = admin_imgPath;
    }

    public int getCity_fk() {
        return city_fk;
    }

    public void setCity_fk(int city_fk) {
        this.city_fk = city_fk;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_fullname='" + admin_fullname + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_dateJoined='" + admin_dateJoined + '\'' +
                ", admin_email='" + admin_email + '\'' +
                ", admin_nickname='" + admin_nickname + '\'' +
                ", admin_imgPath='" + admin_imgPath + '\'' +
                ", city_fk=" + city_fk +
                '}';
    }
}
