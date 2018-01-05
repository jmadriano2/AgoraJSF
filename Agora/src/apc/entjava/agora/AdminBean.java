package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ProjectDao;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.ProjectService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ManagedBean
public class AdminBean {
    private List<Projects> city_projects;
    private Part project_image;
    private String project_name;
    private String project_description;
    private String project_address;
    private String project_imgPath;
    private double budget_materials;
    private double budget_operations;
    private double budget_management;
    private double budget_labor;
    private double budget_misc;


    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    //Services
    private ProjectService projectService = new ProjectDao();

    @PostConstruct
    public void init() {
        int adminCity = authBean.getLoggedAdmin().getCity_fk();
        String city = projectService.getAdminCity(adminCity);

        city_projects = projectService.getProjectInfo(city);
    }

    //Getters and Setters

    public List<Projects> getCity_projects() {
        return city_projects;
    }

    public Part getProject_image() {
        return project_image;
    }

    public void setProject_image(Part project_image) {
        this.project_image = project_image;
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

    public void setCity_projects(List<Projects> city_projects) {
        this.city_projects = city_projects;
    }

    public double getBudget_materials() {
        return budget_materials;
    }

    public void setBudget_materials(double budget_materials) {
        this.budget_materials = budget_materials;
    }

    public double getBudget_operations() {
        return budget_operations;
    }

    public void setBudget_operations(double budget_operations) {
        this.budget_operations = budget_operations;
    }

    public double getBudget_management() {
        return budget_management;
    }

    public void setBudget_management(double budget_management) {
        this.budget_management = budget_management;
    }

    public double getBudget_labor() {
        return budget_labor;
    }

    public void setBudget_labor(double budget_labor) {
        this.budget_labor = budget_labor;
    }

    public double getBudget_misc() {
        return budget_misc;
    }

    public void setBudget_misc(double budget_misc) {
        this.budget_misc = budget_misc;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    //methods
    public String postProject() {
        return "PostProject";
    }

    public String projectPosted() {
        project_imgPath = "resources/images/projects/" + project_image.getSubmittedFileName();
        int adminCity = authBean.getLoggedAdmin().getCity_fk();
        int adminId = authBean.getLoggedAdmin().getAdmin_id();
        projectService.postProject(project_name, project_description, project_address, project_imgPath, budget_materials,
                budget_operations, budget_management, budget_labor, budget_misc, adminId, adminCity);

        String city = projectService.getAdminCity(adminCity);
        city_projects = projectService.getProjectInfo(city);

        return "ProjectPosted";
    }
}
