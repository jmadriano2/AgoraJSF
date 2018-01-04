package apc.entjava.agora.dataobjects;

import apc.entjava.agora.services.SidebarService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SidebarDao implements SidebarService {
    private DataSource ds;
    private PreparedStatement stmt = null;
    private Connection conn = null;

    public SidebarDao() {
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
    public List<String> selectMyCities(String username){
        List<String> myCities = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT city_fk from users_has_cities WHERE user_fk=? ORDER BY is_home DESC LIMIT 2"
             )) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    myCities.add(rs.getString("city_fk"));
                }
                return myCities;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }
    public List<String> selectNearbyCities(String city_name, String username){
        List<String> nearbyCities = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT city_nearby from cities_nearby WHERE city_fk=? " +
                             "AND city_nearby NOT IN " +
                             "(SELECT city_fk from users_has_cities WHERE user_fk=? ORDER BY is_home DESC)" +
                             "LIMIT 2"
             )) {
            stmt.setString(1, city_name);
            stmt.setString(2, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    nearbyCities.add(rs.getString("city_nearby"));
                }
                return nearbyCities;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }
    public List<String> selectMyNeighbours(String city_name, String username){
        List<String> myNeighbours = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT  user_fk FROM users_has_cities " +
                             "WHERE city_fk=? AND is_home=1 AND user_fk!=? "
             )) {
            stmt.setString(1, city_name);
            stmt.setString(2, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    myNeighbours.add(rs.getString("user_fk"));
                }
                return myNeighbours;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }
}
