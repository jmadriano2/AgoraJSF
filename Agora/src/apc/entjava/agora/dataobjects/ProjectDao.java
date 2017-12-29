package apc.entjava.agora.dataobjects;

import apc.entjava.agora.Budget;
import apc.entjava.agora.Mood;
import apc.entjava.agora.Projects;
import apc.entjava.agora.services.ProjectService;

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

public class ProjectDao implements ProjectService{
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
    public List<Projects> getProjectInfo() {
        List<Projects> info = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT projects.*, admins.admin_fullname, cities.city_name " +
                             "FROM cities " +
                             "INNER JOIN admins ON admins.city_fk = cities.city_id " +
                             "INNER JOIN projects ON projects.city_fk = admins.city_fk " +
                             "ORDER BY project_dateposted DESC"
             )) {
            try(ResultSet rs = stmt.executeQuery()) {
                System.out.println(rs);
                while (rs.next()){
                    String project_name = rs.getString("project_name");
                    String project_description = rs.getString("project_description");
                    String project_address = rs.getString("project_address");
                    String project_dateposted = rs.getString("project_datePosted");
                    String project_imgpath = rs.getString("project_imgpath");
                    String project_admin = rs.getString("admins.admin_fullname");
                    String project_city = rs.getString("cities.city_name");
                    int project_index = info.size();
                    info.add(new Projects(project_index, project_name, project_description,
                            project_address, project_dateposted,
                            project_imgpath,project_admin, project_city));
                    System.out.println("inside while loop");
                    System.out.println(rs.getString("project_name"));
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

    public List<Budget> getBudget(int project_index) {
        List<Budget> budget = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT budget_materials, budget_operations, budget_management, budget_labor, budget_misc " +
                             "FROM projects WHERE project_id =?"
             )) {

            stmt.setInt(1, project_index);

            try(ResultSet rs = stmt.executeQuery()) {
                System.out.println(rs);
                while (rs.next()){

                    String budget_materials = rs.getString("budget_materials");
                    String budget_operations = rs.getString("budget_operations");
                    String budget_management = rs.getString("budget_management");
                    String budget_labor = rs.getString("budget_labor");
                    String budget_misc = rs.getString("budget_misc");

                    budget.add(new Budget(budget_materials,budget_operations,budget_management,budget_labor,budget_misc));
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

    public List<Mood> getMood(int project_index) {
        List<Mood> mood = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT mood_happy, mood_sad, mood_angry, mood_disgusted, mood_fearful " +
                             "FROM projects WHERE project_id =?"
             )) {

            stmt.setInt(1, project_index);

            try(ResultSet rs = stmt.executeQuery()) {
                System.out.println(rs);
                while (rs.next()){

                    String mood_happy = rs.getString("mood_happy");
                    String mood_sad = rs.getString("mood_sad");
                    String mood_angry = rs.getString("mood_angry");
                    String mood_disgusted = rs.getString("mood_disgusted");
                    String mood_fearful = rs.getString("mood_fearful");

                    mood.add(new Mood(mood_happy,mood_sad,mood_angry,mood_disgusted,mood_fearful));
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
}
