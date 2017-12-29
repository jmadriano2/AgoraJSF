package apc.entjava.agora.dataobjects;

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
                    info.add(new Projects(project_name, project_description,
                            project_address, project_dateposted,
                            project_imgpath,project_admin, project_city));
                    System.out.println("inside while loop");
                    System.out.println(rs.getString("project_name"));
                    System.out.println(info.size());
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
}
