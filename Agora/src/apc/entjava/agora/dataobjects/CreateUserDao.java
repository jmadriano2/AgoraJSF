package apc.entjava.agora.dataobjects;

import apc.entjava.agora.services.CreateUserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class CreateUserDao implements CreateUserService {
    private DataSource ds;

    public CreateUserDao() {
        Context context = null;
        try {
            context = new InitialContext();
            this.ds = (DataSource) context.lookup("java:comp/env/jdbc/agoraDB");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //INSERT INTO `users` (`user_id`, `user_name`, `user_fname`, `user_lname`, `user_password`, `user_dateJoined`,
    // `user_email`) VALUES (NULL, 'linksparrow', 'Jade Ericson', 'Adriano', 'password', CURRENT_TIMESTAMP,
    // 'adrianojason12@gmail.com');


    @Override
    public boolean createUser(String fname, String lname, String username, String email, String password) {

        int i = 0;
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO users(user_id, user_name, user_fname, user_lname, user_password" +
                            ", user_dateJoined, user_email, user_nickname) VALUES(NULL, ?, ?, ?, ?, " +
                            "CURRENT_TIMESTAMP , ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, fname);
            stmt.setString(3, lname);
            stmt.setString(4, password);
            stmt.setString(5, email);
            stmt.setString(6, fname);
            i = stmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            connCLose(stmt, conn);
        }
        return i > 0;
    }

    static void connCLose(PreparedStatement stmt, Connection conn) {
        try {
            assert conn != null;
            conn.close();
            assert stmt != null;
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//    public String add() {
//        int i = 0;
//
//        PreparedStatement ps = null;
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
//            String sql = "INSERT INTO user1(userId, name, address, created_date) VALUES(?,?,?,)";
//            ps = con.prepareStatement(sql);
//            ps.setLong(1, userID);
//            ps.setString(2, name);
//            ps.setString(3, address);
//            Object obj = date;
//            if (obj == null) {
//                ps.setDate(4, null);
//            } else {
//                java.sql.Date dt = java.sql.Date.valueOf(new String(date));
//                ps.setDate(4, dt);
//            }
//            i = ps.executeUpdate();
//            System.out.println("Data Added Successfully");
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                con.close();
//                ps.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if (i > 0) {
//            return "output";
//        } else {
//            return "invalid";
//        }
//    }
//}