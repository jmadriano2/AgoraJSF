package apc.entjava.agora.services;

import apc.entjava.agora.objects.Comments;

import java.util.List;

public interface CommentService {
    List<Comments> getCommentsList();
    void insertComment(String comment_text, String comment_mood, int user_id, int project_id);
    boolean userUpvotedComment(int comment_id, int user_id);
    void insertUpvote(int comment_id, int user_id);
    int selectUpvote(int comment_id, int user_id);
    void upvote(int comment_id, int user_id);
    void increaseUpvote(int comment_id);
    void downvote(int comment_id, int user_id);
    void decreaseUpvote(int comment_id);
}
