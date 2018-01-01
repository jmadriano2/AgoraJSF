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

    public void updateMood(String input, String happy, String sad, String angry, String disgusted, String fearful) {
        UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
        int mood = 5;
        int user_id = authBean.getLoggedUser().getUser_id();
        int project_id = detailBean.getDetail().getProject_id();
        int h = Integer.parseInt(happy);
        int s = Integer.parseInt(sad);
        int a = Integer.parseInt(angry);
        int d = Integer.parseInt(disgusted);
        int f = Integer.parseInt(fearful);
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
        System.out.println("Happy votes: " + happy);
        System.out.println("Sad votes: " + sad);
        System.out.println("Angry votes: " + angry);
        System.out.println("Disgusted votes: " + disgusted);
        System.out.println("Fearful votes: " + fearful);
        reactionService.updateMood(mood, user_id, project_id);
        reactionService.updateMoodVotes(project_id, h, s, a, d, f);
    }
}
