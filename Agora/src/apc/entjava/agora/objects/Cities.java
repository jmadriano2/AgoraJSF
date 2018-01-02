package apc.entjava.agora.objects;

public class Cities {
    private int city_index;
    private int city_id;
    private String city_name;
    private String city_imgpath;

    public Cities(int city_index, int city_id, String city_name, String city_imgpath) {
        this.city_index = city_index;
        this.city_id = city_id;
        this.city_name = city_name;
        this.city_imgpath = city_imgpath;
    }

    public int getCity_index() {
        return city_index;
    }

    public void setCity_index(int city_index) {
        this.city_index = city_index;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_imgpath() {
        return city_imgpath;
    }

    public void setCity_imgpath(String city_imgpath) {
        this.city_imgpath = city_imgpath;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "city_index=" + city_index +
                ", city_id=" + city_id +
                ", city_name='" + city_name + '\'' +
                ", city_imgpath='" + city_imgpath + '\'' +
                '}';
    }
}
