package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ProjectDao;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.ProjectService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean
public class ProjectBean {

    private List<Projects> project_info;

    private ProjectService projectService = new ProjectDao();

    @PostConstruct
    public void init() {
        project_info = projectService.getProjectInfo();

        for(int i = 0; i < project_info.size(); i++){
            Object next = project_info.get(i);
            System.out.println(next);
        }
    }

    public List<Projects> getProject_info() {
        return project_info;
    }

    public void setProject_info(List<Projects> project_info) {
        this.project_info = project_info;
    }

    @ManagedProperty(value = "#{detailBean}")
        private DetailBean detailBean;

    public DetailBean getDetailBean() {
        return detailBean;
    }

    public void setDetailBean(DetailBean detailBean) {
        this.detailBean = detailBean;
    }

    public String passDetails(int project_index, int project_id) {
        detailBean.setDetail(project_info.get(project_index));
        Projects detail = detailBean.getDetail();
        detailBean.setBudget(projectService.getBudget(project_id));
        detailBean.setMood(projectService.getMood(project_id));
        return "Details";
    }
}
