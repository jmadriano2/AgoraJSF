package apc.entjava.agora.dataobjects;

import apc.entjava.agora.objects.Comments;
import apc.entjava.agora.services.CommentService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements CommentService {
    private DataSource ds;

    public CommentDao() {
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
    public List<Comments> getCommentsList() {
        List<Comments> commentsList = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT comments.*, users.user_nickname, users.user_imgPath " +
                             "FROM comments " +
                             "INNER JOIN users ON users.user_id = comments.user_fk " +
                             "ORDER BY comments_upvotes DESC, comments_datePosted DESC "
             )) {

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat myFormat = new SimpleDateFormat("MMM d, yyyy 'at' h:mm a");
                Timestamp datetime;
                while (rs.next()) {
                    datetime = rs.getTimestamp("comments_datePosted");
                    int comment_index = commentsList.size() + 1;
                    int comment_id = rs.getInt("comments_id");
                    String comment_text = rs.getString("comments_text");
                    String comment_datePosted = myFormat.format(datetime);
                    String comment_mood = rs.getString("comments_mood");
                    int comment_upvotes = rs.getInt("comments_upvotes");
                    String comment_poster = rs.getString("user_nickname");
                    String comment_posterImg = rs.getString("user_imgPath");

                    commentsList.add(new Comments(comment_index, comment_id, comment_text, comment_datePosted,
                            comment_mood, comment_upvotes, comment_poster, comment_posterImg));
                }
                return commentsList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertComment(String comment_text, String comment_mood, int user_id, int project_id) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO comments(comments_id, comments_text, comments_datePosted, comments_mood, project_fk, user_fk) " +
                            "VALUES(NULL, ?, CURRENT_TIMESTAMP, ?, ?, ?)");
            stmt.setString(1, comment_text);
            stmt.setString(2, comment_mood);
            stmt.setInt(3, project_id);
            stmt.setInt(4, user_id);
            stmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public boolean userUpvotedComment(int comment_id, int user_id){
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM user_upvoted WHERE comments_fk=? AND user_fk=?"
             )) {

            stmt.setInt(1, comment_id);
            stmt.setInt(2, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertUpvote(int comment_id, int user_id){
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO user_upvoted(id, comments_fk, user_fk, upvote) " +
                            "VALUES(NULL, ?, ?, 1)");
            stmt.setInt(1, comment_id);
            stmt.setInt(2, user_id);
            stmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public int selectUpvote(int comment_id, int user_id){
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT upvote FROM user_upvoted WHERE comments_fk=? AND user_fk=?"
             )) {

            stmt.setInt(1, comment_id);
            stmt.setInt(2, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt("upvote");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void upvote(int comment_id, int user_id){
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE user_upvoted SET upvote = 1 WHERE comments_fk=? AND user_fk=?");
            stmt.setInt(1, comment_id);
            stmt.setInt(2, user_id);
            stmt.executeUpdate();
            System.out.println("Data Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public void increaseUpvote(int comment_id){
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE comments SET comments_upvotes=comments_upvotes+1 WHERE comments_id=?");
            stmt.setInt(1, comment_id);
            stmt.executeUpdate();
            System.out.println("Data Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public void downvote(int comment_id, int user_id){
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE user_upvoted SET upvote = 0 WHERE comments_fk=? AND user_fk=?");
            stmt.setInt(1, comment_id);
            stmt.setInt(2, user_id);
            stmt.executeUpdate();
            System.out.println("Data Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public void decreaseUpvote(int comment_id){
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE comments SET comments_upvotes=comments_upvotes-1 WHERE comments_id=?");
            stmt.setInt(1, comment_id);
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
