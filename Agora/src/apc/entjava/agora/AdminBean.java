package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ProjectDao;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.ProjectService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class AdminBean {
    List<Projects> city_projects;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    //Services
    private ProjectService projectService = new ProjectDao();

    @PostConstruct
    public void init(){
        int adminCity = authBean.getLoggedAdmin().getCity_fk();
        String city = projectService.getAdminCity(adminCity);

        city_projects = projectService.getProjectInfo(city);
    }

    //Getters and Setters

    public List<Projects> getCity_projects() {
        return city_projects;
    }

    public void setCity_projects(List<Projects> city_projects) {
        this.city_projects = city_projects;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }
}
