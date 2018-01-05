package apc.entjava.agora.services;

import apc.entjava.agora.objects.Budget;
import apc.entjava.agora.objects.Mood;
import apc.entjava.agora.objects.Projects;

import java.util.List;

public interface ProjectService {
    String getHomeCity(String username);

    String getAdminCity(int adminCity);

    List<Projects> getHomeProjectInfo(String homeCity);

    boolean userHasCities(String username);

    List<String> getUserCities(String username);

    List<Projects> getProjectInfo(String city);

    Budget getBudget(int project_index);

    Mood getMood(int project_index);

    void postProject(String project_name, String project_description, String project_address, String project_imgPath,
                double budget_materials, double budget_operations, double budget_management,
                double budget_labor, double budget_misc, int adminId, int adminCity);
}
