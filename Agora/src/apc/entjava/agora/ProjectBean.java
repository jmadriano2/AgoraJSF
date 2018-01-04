package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ProjectDao;
import apc.entjava.agora.dataobjects.MoodDao;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.ProjectService;
import apc.entjava.agora.services.MoodService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class ProjectBean {

    private List<Projects> project_info;
    private List<String> user_cities;

    //Services
    private ProjectService projectService = new ProjectDao();
    private MoodService moodService = new MoodDao();

    @ManagedProperty(value = "#{detailBean}")
    private DetailBean detailBean;
    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;


    @PostConstruct
    public void init() {
        String username = authBean.getLoggedUser().getUser_name();
        String homeCity = projectService.getHomeCity(username);
        
        project_info = projectService.getHomeProjectInfo(homeCity);

        if(projectService.userHasCities(username)){
            user_cities = projectService.getUserCities(username);
            for (String city: user_cities) {
                project_info.addAll(projectService.getProjectInfo(city));
            }
            int i = 0;
            for (Projects project: project_info){
                project_info.get(i).setProject_index(i);
                i++;
            }
        }
    }

    //Getters and Setters
    public List<Projects> getProject_info() {
        return project_info;
    }

    public DetailBean getDetailBean() {
        return detailBean;
    }

    public void setDetailBean(DetailBean detailBean) {
        this.detailBean = detailBean;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    //Methods
    public String passDetails(int project_index, int project_id) {
        setDetail(project_index);
        setDetailBudget(project_id);
        setDetailMood(project_id);
        return "Details";
    }

    private void setDetailMood(int project_id) {
        detailBean.setMood(projectService.getMood(project_id));
    }

    private void setDetailBudget(int project_id) {
        detailBean.setBudget(projectService.getBudget(project_id));
    }

    private void setDetail(int project_index) {
        detailBean.setDetail(project_info.get(project_index));
    }
}
