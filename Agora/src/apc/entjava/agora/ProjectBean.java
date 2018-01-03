package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ProjectDao;
import apc.entjava.agora.dataobjects.ReactionDao;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.ProjectService;
import apc.entjava.agora.services.ReactionService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
public class ProjectBean {

    private List<Projects> project_info;
    private List<String> user_cities;

    private ProjectService projectService = new ProjectDao();
    private ReactionService reactionService = new ReactionDao();

    @ManagedProperty(value = "#{detailBean}")
    private DetailBean detailBean;
    @ManagedProperty(value = "#{reactionBean}")
    private ReactionBean reactionBean;
    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;


    @PostConstruct
    public void init() {
        String username = authBean.getLoggedUser().getUser_name();
        String homeCity = projectService.getHomeCity(username);
        
        project_info = projectService.getHomeProjectInfo(username, homeCity);

        if(projectService.userHasCities(username)){
            user_cities = projectService.getUserCities(username);
            for (String city: user_cities) {
                project_info.addAll(projectService.getProjectInfo(username, city));
            }
            int i = 0;
            for (Projects project: project_info){
                project_info.get(i).setProject_index(i);
                i++;
            }
        }
    }

    public List<Projects> getProject_info() {
        return project_info;
    }

    public void setProject_info(List<Projects> project_info) {
        this.project_info = project_info;
    }

    public DetailBean getDetailBean() {
        return detailBean;
    }

    public void setDetailBean(DetailBean detailBean) {
        this.detailBean = detailBean;
    }

    public ReactionBean getReactionBean() {
        return reactionBean;
    }

    public void setReactionBean(ReactionBean reactionBean) {
        this.reactionBean = reactionBean;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    public String passDetails(int project_index, int project_id) {
        setDetail(project_index);
        setDetailBudget(project_id);
        setDetailMood(project_id);

        int user_id = authBean.getLoggedUser().getUser_id();
        if (!reactionService.userHasMood(user_id, project_id)) {
            createReaction(user_id, project_id);
        }

        setUserMood(user_id, project_id);

        return "Details";
    }

    private void setUserMood(int user_id, int project_id) {
        reactionBean.setUser_mood(reactionService.userMood(user_id, project_id));
    }

    private void createReaction(int user_id, int project_id) {
        reactionService.createMood(user_id, project_id);
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
