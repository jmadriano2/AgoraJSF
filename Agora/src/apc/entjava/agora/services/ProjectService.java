package apc.entjava.agora.services;

import apc.entjava.agora.objects.Budget;
import apc.entjava.agora.objects.Mood;
import apc.entjava.agora.objects.Projects;

import java.util.List;

public interface ProjectService {
    String getHomeCity(String username);

    List<Projects> getHomeProjectInfo(String username, String homeCity);

    boolean userHasCities(String username);

    List<String> getUserCities(String username);

    List<Projects> getProjectInfo(String username, String city);

    Budget getBudget(int project_index);

    Mood getMood(int project_index);
}
