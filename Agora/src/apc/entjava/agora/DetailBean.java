package apc.entjava.agora;

import apc.entjava.agora.objects.Budget;
import apc.entjava.agora.objects.Mood;
import apc.entjava.agora.objects.Projects;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DetailBean {
    private Projects detail;
    private Budget budget;
    private Mood mood;

    public Projects getDetail() {
        return detail;
    }

    public void setDetail(Projects detail) {
        this.detail = detail;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }
}
