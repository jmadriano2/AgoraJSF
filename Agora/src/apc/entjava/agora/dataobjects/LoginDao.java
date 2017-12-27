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

    public LoginDao() {
        Context context = null;
        try {
            context = new InitialContext();
            this.ds = (DataSource)context.lookup("java:comp/env/jdbc/agoraDB");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login(String username, String password) {
        try(Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT user_nickname FROM users WHERE user_name=? AND user_password=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String nickname(String username, String password) {
        String name = "";
        try(Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT user_nickname FROM users WHERE user_name=? AND user_password=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    name = rs.getString("user_nickname");
                    return name;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return name;
    }
}
