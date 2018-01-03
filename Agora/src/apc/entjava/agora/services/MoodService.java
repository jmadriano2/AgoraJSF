package apc.entjava.agora.services;

public interface MoodService {
    void createMood(int user_id, int project_id);

    boolean userHasMood(int user_id, int project_id);

    int userMood(int user_id, int project_id);

    void updateMood(int user_mood, int user_id, int project_id);

    void updateMoodVotes(int project_id, int happy, int sad, int angry, int disgusted, int fearful);
}
