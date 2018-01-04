package apc.entjava.agora.services;

import apc.entjava.agora.objects.Comments;

import java.util.List;

public interface CommentService {
    List<Comments> getCommentsList();
    void insertComment(String comment_text, String comment_mood, int user_id, int project_id);
}
