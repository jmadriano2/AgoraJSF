package apc.entjava.agora.services;

import apc.entjava.agora.objects.Budget;
import apc.entjava.agora.objects.Mood;
import apc.entjava.agora.objects.Projects;

import java.util.List;

public interface ProjectService {
    List<Projects> getProjectInfo();
    Budget getBudget(int project_index);
    Mood getMood(int project_index);
}
