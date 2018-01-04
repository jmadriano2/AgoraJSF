package apc.entjava.agora;

import apc.entjava.agora.dataobjects.MoodDao;
import apc.entjava.agora.services.MoodService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class MoodBean {
    private int user_mood;
    private String user_mood_string;

    private MoodService moodService = new MoodDao();

    public int getUser_mood() {
        return user_mood;
    }

    public void setUser_mood(int user_mood) {
        this.user_mood = user_mood;
    }

    public String getUser_mood_string() {
        return user_mood_string;
    }

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;
    @ManagedProperty(value = "#{detailBean}")
    private DetailBean detailBean;

    @PostConstruct
    public void init() {
        int user_id = authBean.getLoggedUser().getUser_id();
        int project_id = detailBean.getDetail().getProject_id();

        if (!moodService.userHasMood(user_id, project_id)) {
            moodService.createMood(user_id, project_id);
        }

        user_mood = moodService.userMood(user_id, project_id);
        updateMoodIcon();
    }

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
        updateSuccess();
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
        moodService.updateMood(mood, user_id, project_id);
        moodService.updateMoodVotes(project_id, h, s, a, d, f);
    }

    private void updateSuccess() {

        FacesContext fc = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage("Your Feelings are Heard", "Your Feelings are Heard");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        fc.addMessage("update:my-mood", msg);
    }

    public void updateMoodIcon(){
        switch (user_mood) {
            case 0:
                user_mood_string = "sad";
                break;
            case 1:
                user_mood_string = "angry";
                break;
            case 2:
                user_mood_string = "happy";
                break;
            case 3:
                user_mood_string = "disgusted";
                break;
            case 4:
                user_mood_string = "fearful";
                break;
        }
    }
}
