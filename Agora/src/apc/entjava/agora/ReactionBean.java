package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ReactionDao;
import apc.entjava.agora.services.ReactionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
public class ReactionBean {
    private int user_mood;

    private ReactionService reactionService = new ReactionDao();

    public int getUser_mood() {
        return user_mood;
    }

    public void setUser_mood(int user_mood) {
        this.user_mood = user_mood;
    }

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;
    @ManagedProperty(value = "#{detailBean}")
    private DetailBean detailBean;

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    public DetailBean getDetailBean() {
        return detailBean;
    }

    public void setDetailBean(DetailBean detailBean) {
        this.detailBean = detailBean;
    }

    public void updateMood(String input){
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        int mood = 5;
        switch (input) {
            case "Sad":
                mood = 0;
                break;
            case "Angry":
                mood = 1;
                break;
            case "Happy":
                mood = 2;
                break;
            case "Disgusted":
                mood = 3;
                break;
            case "Fearful":
                mood = 4;
                break;
        }
        System.out.println("my-mood: " + input);
        System.out.println("mood: " + mood);
        System.out.println("user-id: " + authBean.getLoggedUser().getUser_id());
        System.out.println("project-id: " + detailBean.getDetail().getProject_id());
        reactionService.updateMood(mood, authBean.getLoggedUser().getUser_id(), detailBean.getDetail().getProject_id());
    }
}
