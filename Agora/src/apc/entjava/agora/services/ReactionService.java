package apc.entjava.agora.services;

public interface ReactionService {
    boolean createMood(int user_id, int project_id);
    boolean userHasMood(int user_id, int project_id);
    int userMood(int user_id, int project_id);
    boolean updateMood(int user_mood, int user_id, int project_id);
    boolean updateMoodVotes(int project_id, int happy, int sad, int angry, int disgusted, int fearful);
}
