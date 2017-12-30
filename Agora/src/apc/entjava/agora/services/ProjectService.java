package apc.entjava.agora.services;

import apc.entjava.agora.Budget;
import apc.entjava.agora.Mood;
import apc.entjava.agora.Projects;

import java.util.List;

public interface ProjectService {
    List<Projects> getProjectInfo();
    Budget getBudget(int project_index);
    Mood getMood(int project_index);
}
