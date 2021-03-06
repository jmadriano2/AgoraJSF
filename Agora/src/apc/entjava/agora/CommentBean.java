package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CommentDao;
import apc.entjava.agora.objects.Comments;
import apc.entjava.agora.services.CommentService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class CommentBean {
    private List<Comments> comment_list;
    private String comment_txt;
    private int comment_list_size;
    private String are_is;
    private String thought_s;

    //Services
    private CommentService commentService = new CommentDao();

    @ManagedProperty(value = "#{detailBean}")
    private DetailBean detailBean;
    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;
    @ManagedProperty(value = "#{moodBean}")
    private MoodBean moodBean;

    @PostConstruct
    public void init() {
        comment_list = commentService.getCommentsList();

        comment_list_size = comment_list.size();
        are_is = "are";
        thought_s = "thoughts";
        if(comment_list_size==1) {
            are_is = "is only";
            thought_s = "thought";
        }
    }

    //Getters and Setters


    public List<Comments> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<Comments> comment_list) {
        this.comment_list = comment_list;
    }

    public String getComment_txt() {
        return comment_txt;
    }

    public void setComment_txt(String comment_txt) {
        this.comment_txt = comment_txt;
    }

    public int getComment_list_size() {
        return comment_list_size;
    }

    public String getAre_is() {
        return are_is;
    }

    public String getThought_s() {
        return thought_s;
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

    public MoodBean getMoodBean() {
        return moodBean;
    }

    public void setMoodBean(MoodBean moodBean) {
        this.moodBean = moodBean;
    }

    //Methods
    public void insertComment(){
        String comment_mood = moodBean.getUser_mood_string();
        int project_id = detailBean.getDetail().getProject_id();
        int user_id = authBean.getLoggedUser().getUser_id();
        if(!comment_txt.isEmpty()){
            commentService.insertComment(comment_txt, comment_mood, user_id, project_id);
        }
        comment_list = commentService.getCommentsList();
        comment_list_size = comment_list.size();
        if(comment_list_size==1) {
            are_is = "is only";
            thought_s = "thought";
        }else {
            are_is = "are";
            thought_s = "thoughts";
        }
        comment_txt = null;
    }

    public void updateUpvote(int comment_index) {
        comment_index--;
        int comment_id = comment_list.get(comment_index).getComment_id();
        int user_id = authBean.getLoggedUser().getUser_id();

        if(!commentService.userUpvotedComment(comment_id, user_id)){
            commentService.insertUpvote(comment_id, user_id);
            commentService.increaseUpvote(comment_id);
            comment_list = commentService.getCommentsList();
            return;
        }

        int vote = commentService.selectUpvote(comment_id, user_id);

        if(vote == 0){
            commentService.upvote(comment_id, user_id);
            commentService.increaseUpvote(comment_id);
            comment_list = commentService.getCommentsList();
        }
        else{
            commentService.downvote(comment_id, user_id);
            commentService.decreaseUpvote(comment_id);
            comment_list = commentService.getCommentsList();
        }
    }
}
