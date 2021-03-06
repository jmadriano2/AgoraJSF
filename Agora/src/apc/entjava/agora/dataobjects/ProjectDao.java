package apc.entjava.agora.dataobjects;

import apc.entjava.agora.objects.Budget;
import apc.entjava.agora.objects.Mood;
import apc.entjava.agora.objects.Projects;
import apc.entjava.agora.services.ProjectService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDao implements ProjectService {
    private DataSource ds;
    private PreparedStatement stmt = null;
    private Connection conn = null;

    public ProjectDao() {
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
    public String getHomeCity(String username) {
        String homeCity;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT city_fk FROM users_has_cities WHERE user_fk=? AND is_home=1"
             )) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                homeCity = rs.getString("city_fk");
                return homeCity;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public String getAdminCity(int adminCity) {
        String admin_city;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT city_name FROM cities WHERE city_id=?"
             )) {
            stmt.setInt(1, adminCity);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                admin_city = rs.getString("city_name");
                return admin_city;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public List<Projects> getHomeProjectInfo(String homeCity) {
        List<Projects> info = new ArrayList<>();

        return getProjects(homeCity, info);
    }

    public List<Projects> getProjectInfo(String city) {
        List<Projects> info = new ArrayList<>();

        return getProjects(city, info);
    }

    private List<Projects> getProjects(String city, List<Projects> info) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT projects.*,  admins.admin_fullname, cities.city_name " +
                             "FROM cities " +
                             "INNER JOIN admins ON admins.city_fk = cities.city_id " +
                             "INNER JOIN projects ON projects.city_fk = admins.city_fk " +
                             "WHERE cities.city_name=?" +
                             "ORDER BY project_dateposted DESC"
             )) {
            stmt.setString(1, city);

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat myFormat = new SimpleDateFormat("MMM d, yyyy 'at' h:mm a");
                Timestamp datetime;
                while (rs.next()) {
                    datetime = rs.getTimestamp("project_datePosted");
                    int project_id = rs.getInt("project_id");
                    String project_name = rs.getString("project_name");
                    String project_description = rs.getString("project_description");
                    String project_address = rs.getString("project_address");
                    String project_dateposted = myFormat.format(datetime);
                    String project_imgpath = rs.getString("project_imgpath");
                    String project_admin = rs.getString("admins.admin_fullname");
                    String project_city = rs.getString("cities.city_name");
                    int project_index = info.size();
                    info.add(new Projects(project_index, project_id, project_name, project_description,
                            project_address, project_dateposted,
                            project_imgpath, project_admin, project_city));
                }
                return info;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public boolean userHasCities(String username) {
        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT city_fk FROM users_has_cities WHERE user_fk=? AND is_home!=1"
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

    public List<String> getUserCities(String username) {
        List<String> userCities = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT city_fk FROM users_has_cities WHERE user_fk=? AND is_home!=1"
             )) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String city = rs.getString("city_fk");
                    userCities.add(city);
                }
                return userCities;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }
    }

    public Budget getBudget(int project_index) {
        Budget budget = null;
        System.out.println(project_index);

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT budget_materials, budget_operations, budget_management, budget_labor, budget_misc " +
                             "FROM projects WHERE project_id =?"
             )) {

            stmt.setInt(1, project_index);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println(rs);
                while (rs.next()) {

                    String budget_materials = rs.getString("budget_materials");
                    String budget_operations = rs.getString("budget_operations");
                    String budget_management = rs.getString("budget_management");
                    String budget_labor = rs.getString("budget_labor");
                    String budget_misc = rs.getString("budget_misc");

                    budget = new Budget(budget_materials, budget_operations, budget_management, budget_labor, budget_misc);
                }
                return budget;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }

    }

    public Mood getMood(int project_index) {
        Mood mood = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT mood_happy, mood_sad, mood_angry, mood_disgusted, mood_fearful " +
                             "FROM projects WHERE project_id =?"
             )) {

            stmt.setInt(1, project_index);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println(rs);
                while (rs.next()) {

                    int mood_happy = rs.getInt("mood_happy");
                    int mood_sad = rs.getInt("mood_sad");
                    int mood_angry = rs.getInt("mood_angry");
                    int mood_disgusted = rs.getInt("mood_disgusted");
                    int mood_fearful = rs.getInt("mood_fearful");

                    mood = new Mood(mood_happy, mood_sad, mood_angry, mood_disgusted, mood_fearful);
                }
                return mood;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            CreateUserDao.closeConnection(stmt, conn);
        }

    }

    public void postProject(String project_name, String project_description, String project_address, String project_imgPath,
                            double budget_materials, double budget_operations, double budget_management,
                            double budget_labor, double budget_misc, int adminId, int adminCity) {

        PreparedStatement stmt = null;
        Connection conn = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO projects(project_id, project_name, project_description, project_address, project_datePosted, " +
                            "project_imgPath, mood_happy, mood_sad, mood_angry, mood_disgusted, mood_fearful, budget_materials, " +
                            "budget_operations, budget_management, budget_labor, budget_misc, admin_fk, city_fk) " +
                            "VALUES(NULL, ?, ?, ?, CURRENT_TIMESTAMP, ?, 1, 1, 1, 1, 1, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, project_name);
            stmt.setString(2, project_description);
            stmt.setString(3, project_address);
            stmt.setString(4, project_imgPath);
            stmt.setDouble(5, budget_materials);
            stmt.setDouble(6, budget_operations);
            stmt.setDouble(7, budget_management);
            stmt.setDouble(8, budget_labor);
            stmt.setDouble(9, budget_misc);
            stmt.setInt(10, adminId);
            stmt.setInt(11, adminCity);
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
