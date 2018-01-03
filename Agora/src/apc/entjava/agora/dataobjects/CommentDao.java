package apc.entjava.agora.dataobjects;

import apc.entjava.agora.objects.Comments;
import apc.entjava.agora.services.CommentService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                             "ORDER BY comments_datePosted DESC"
             )) {

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat myFormat = new SimpleDateFormat("MMM d, yyyy 'at' h:mm a");
                Date date;
                while (rs.next()) {
                    date = rs.getDate("comments_datePosted");
                    int comment_index = commentsList.size() + 1;
                    int comment_id = rs.getInt("comments_id");
                    String comment_text = rs.getString("comments_text");
                    String comment_datePosted = myFormat.format(date);
                    String comment_poster = rs.getString("user_nickname");
                    String comment_posterImg = rs.getString("user_imgPath");

                    commentsList.add(new Comments(comment_index, comment_id, comment_text, comment_datePosted,
                            comment_poster, comment_posterImg));
                }
                return commentsList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertComment(String comment_text, int user_id, int project_id) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO comments(comments_id, comments_text, comments_datePosted, project_fk, user_fk) " +
                            "VALUES(NULL, ?, CURRENT_TIMESTAMP, ?, ?)");
            stmt.setString(1, comment_text);
            stmt.setInt(2, project_id);
            stmt.setInt(3, user_id);
            stmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }
}
