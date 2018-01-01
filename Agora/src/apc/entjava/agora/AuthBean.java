package apc.entjava.agora;


import apc.entjava.agora.objects.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ManagedBean
@SessionScoped

public class AuthBean implements Serializable{

    private String loggedUsername;
    private User loggedUser;

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername) {
        this.loggedUsername = loggedUsername;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String logout() {
        System.out.println("I was here");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("I was here too");
        return "Logout";
    }
}
