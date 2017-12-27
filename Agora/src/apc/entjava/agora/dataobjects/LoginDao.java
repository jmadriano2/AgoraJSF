package apc.entjava.agora.dataobjects;

import apc.entjava.agora.services.LoginService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao implements LoginService {
    private DataSource ds;
    private PreparedStatement stmt = null;
    private Connection conn = null;

    public LoginDao() {
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
    public boolean login(String username, String password) {

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT user_nickname FROM users WHERE user_name=? AND user_password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.next();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConn();
        }
        return false;
    }

    public String nickname(String username, String password) {
        String name = "";

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT user_nickname FROM users WHERE user_name=? AND user_password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("user_nickname");
                    return name;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConn();
        }
        return name;
    }

    private void closeConn() {
        CreateUserDao.connCLose(stmt, conn);
    }
}
