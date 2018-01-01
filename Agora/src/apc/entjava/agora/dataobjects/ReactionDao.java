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

public class ReactionDao implements ReactionService{
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

//    @Override
//    public boolean login(String username, String password) {
//        try (Connection conn = ds.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(
//                     "SELECT * FROM users WHERE user_name=? AND user_password=?"
//             )) {
//
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//
//            try(ResultSet rs = stmt.executeQuery()) {
//                return rs.next();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            CreateUserDao.closeConnection(stmt, conn);
//        }
//    }
}
