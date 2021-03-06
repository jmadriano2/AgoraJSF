package apc.entjava.agora.dataobjects;

import apc.entjava.agora.objects.Admin;
import apc.entjava.agora.objects.User;
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
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE user_name=? AND user_password=?"
             )) {

            stmt.setString(1, username);
            stmt.setString(2, password);

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

    public User loggedUser(String username) {
        User user = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE user_name=?"
             )) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String user_name = rs.getString("user_name");
                    String user_fname = rs.getString("user_fname");
                    String user_lname = rs.getString("user_lname");
                    String user_dateJoined = rs.getString("user_dateJoined");
                    String user_email = rs.getString("user_email");
                    String user_nickname = rs.getString("user_nickname");
                    String user_imgpath = rs.getString("user_imgpath");

                    user = new User(user_id, user_name, user_fname, user_lname, user_dateJoined, user_email, user_nickname, user_imgpath);
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
        return user;
    }

    public boolean loginadmin(String adminname, String password) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM admins WHERE admin_name=? AND admin_password=?"
             )) {

            stmt.setString(1, adminname);
            stmt.setString(2, password);

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

    public Admin loggedAdmin(String adminname) {
        Admin admin = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM admins WHERE admin_name=?"
             )) {

            stmt.setString(1, adminname);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int admin_id = rs.getInt("admin_id");
                    String admin_fullname = rs.getString("admin_fullname");
                    String admin_name = rs.getString("admin_name");
                    String admin_dateJoined = rs.getString("admin_dateJoined");
                    String admin_email = rs.getString("admin_email");
                    String admin_nickname = rs.getString("admin_nickname");
                    String admin_imgPath = rs.getString("admin_imgPath");
                    int city_fk = rs.getInt("city_fk");

                    admin = new Admin(admin_id, admin_fullname, admin_name, admin_dateJoined, admin_email,
                            admin_nickname, admin_imgPath, city_fk);
                    return admin;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
        return admin;
    }

}
