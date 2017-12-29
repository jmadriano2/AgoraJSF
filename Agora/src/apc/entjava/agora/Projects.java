package apc.entjava.agora;

public class Projects {
    private String project_name;
    private String project_description;
    private String project_address;
    private String project_dateposted;
    private String project_imgpath;
    private String project_admin;
    private String project_city;

    public Projects(String project_name, String project_description, String project_address,
                       String project_dateposted, String project_imgpath, String project_admin,
                       String project_city) {
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_address = project_address;
        this.project_dateposted = project_dateposted;
        this.project_imgpath = project_imgpath;
        this.project_admin = project_admin;
        this.project_city = project_city;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProject_address() {
        return project_address;
    }

    public void setProject_address(String project_address) {
        this.project_address = project_address;
    }

    public String getProject_dateposted() {
        return project_dateposted;
    }

    public void setProject_dateposted(String project_dateposted) {
        this.project_dateposted = project_dateposted;
    }

    public String getProject_imgpath() {
        return project_imgpath;
    }

    public void setProject_imgpath(String project_imgpath) {
        this.project_imgpath = project_imgpath;
    }

    public String getProject_admin() {
        return project_admin;
    }

    public void setProject_admin(String project_admin) {
        this.project_admin = project_admin;
    }

    public String getProject_city() {
        return project_city;
    }

    public void setProject_city(String project_city) {
        this.project_city = project_city;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "project_name='" + project_name + '\'' +
                ", project_description='" + project_description + '\'' +
                ", project_address='" + project_address + '\'' +
                ", project_dateposted='" + project_dateposted + '\'' +
                ", project_imgpath='" + project_imgpath + '\'' +
                ", project_admin='" + project_admin + '\'' +
                ", project_city='" + project_city + '\'' +
                '}';
    }
}
