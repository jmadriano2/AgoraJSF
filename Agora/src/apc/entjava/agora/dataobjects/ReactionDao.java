package apc.entjava.agora.dataobjects;

import apc.entjava.agora.services.ReactionService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReactionDao implements ReactionService {
    private DataSource ds;
    private PreparedStatement stmt = null;
    private Connection conn = null;

    public ReactionDao() {
        Context context = null;
        try {
            context = new InitialContext();
            this.ds = (DataSource) context.lookup("java:comp/env/jdbc/agoraDB");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createMood(int user_id, int project_id) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO user_has_mood(id, user_fk, project_fk, user_mood) VALUES(NULL, ?, ?, 5 )");
            stmt.setInt(1, user_id);
            stmt.setInt(2, project_id);
            stmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public boolean userHasMood(int user_id, int project_id) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM user_has_mood WHERE user_fk=? AND project_fk=?"
             )) {

            stmt.setInt(1, user_id);
            stmt.setInt(2, project_id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public int userMood(int user_id, int project_id) {
        int user_mood;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT user_mood FROM user_has_mood WHERE user_fk=? AND project_fk=?"
             )) {

            stmt.setInt(1, user_id);
            stmt.setInt(2, project_id);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                user_mood = rs.getInt("user_mood");
                return user_mood;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public void updateMood(int user_mood, int user_id, int project_id) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE user_has_mood SET user_mood = ? WHERE user_fk=? AND project_fk=?");
            stmt.setInt(1, user_mood);
            stmt.setInt(2, user_id);
            stmt.setInt(3, project_id);
            stmt.executeUpdate();
            System.out.println("Data Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public void updateMoodVotes(int project_id, int happy, int sad, int angry, int disgusted, int fearful){
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE projects SET mood_happy=?, mood_sad=?, mood_angry=?, mood_disgusted=?, mood_fearful=? " +
                            "WHERE project_id=?");
            stmt.setInt(1, happy);
            stmt.setInt(2, sad);
            stmt.setInt(3, angry);
            stmt.setInt(4, disgusted);
            stmt.setInt(5, fearful);
            stmt.setInt(6, project_id);
            stmt.executeUpdate();
            System.out.println("Data Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }
}
