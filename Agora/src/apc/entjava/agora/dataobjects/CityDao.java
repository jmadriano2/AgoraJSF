package apc.entjava.agora.dataobjects;

import apc.entjava.agora.objects.Cities;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.CityService;

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

public class CityDao implements CityService {
    private DataSource ds;
    private PreparedStatement stmt = null;
    private Connection conn = null;

    public CityDao() {
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
    public List<Cities> selectCities() {
        List<Cities> cities = new ArrayList<>();
        int index = 0;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM  cities"
             )) {
            return getCities(cities, index, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public List<Cities> selectChooseCities(String username) {
        List<Cities> userCities = new ArrayList<>();
        int index = 0;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM cities  " +
                             "WHERE city_name NOT IN( " +
                             "SELECT cities.city_name FROM cities " +
                             "LEFT JOIN users_has_cities ON users_has_cities.city_fk = cities.city_name " +
                             "WHERE users_has_cities.user_fk=?)"
             )) {

            stmt.setString(1, username);

            return getCities(userCities, index, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    private List<Cities> getCities(List<Cities> userCities, int index, PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int city_id = rs.getInt("city_id");
                String city_name = rs.getString("city_name");
                String city_imgpath = rs.getString("city_imgpath");
                userCities.add(new Cities(index, city_id, city_name, city_imgpath));
                index++;
            }
            return userCities;
        }
    }

    public boolean userHasCity(String username) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users_has_cities WHERE user_fk=?"
             )) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertHomeCity(String username, String city_name) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO users_has_cities(id, user_fk, city_fk, is_home) VALUES(NULL, ?, ?, 1 )");
            stmt.setString(1, username);
            stmt.setString(2, city_name);
            stmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public void insertCity(String username, String city_name) {
        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO users_has_cities(id, user_fk, city_fk, is_home) VALUES(NULL, ?, ?, 0 )");
            stmt.setString(1, username);
            stmt.setString(2, city_name);
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
