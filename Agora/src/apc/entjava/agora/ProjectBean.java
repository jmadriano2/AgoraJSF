package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ProjectDao;
import apc.entjava.agora.services.ProjectService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
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
}
