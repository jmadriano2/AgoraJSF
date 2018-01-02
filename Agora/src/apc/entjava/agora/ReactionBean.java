package apc.entjava.agora;

import apc.entjava.agora.dataobjects.ReactionDao;
import apc.entjava.agora.services.ReactionService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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

    public ReactionService getReactionService() {
        return reactionService;
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

    public void updateMood(String input, int h, int s, int a, int d, int f) {
        int mood = 5;
        int user_id = authBean.getLoggedUser().getUser_id();
        int project_id = detailBean.getDetail().getProject_id();
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
        System.out.println("Happy votes: " + h);
        System.out.println("Sad votes: " + s);
        System.out.println("Angry votes: " + a);
        System.out.println("Disgusted votes: " + d);
        System.out.println("Fearful votes: " + f);
        reactionService.updateMood(mood, user_id, project_id);
        reactionService.updateMoodVotes(project_id, h, s, a, d, f);
    }

    public void updateSuccess() {

        FacesContext fc = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage("Your Feelings are Heard", "Your Feelings are Heard");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        fc.addMessage("update:my-mood", msg);
    }
}
