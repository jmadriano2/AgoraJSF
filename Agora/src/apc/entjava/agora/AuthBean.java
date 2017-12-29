package apc.entjava.agora;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ManagedBean
@SessionScoped

public class AuthBean implements Serializable{

    private String loggedUsername;

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername) {
        this.loggedUsername = loggedUsername;
    }

    public String logout() {
        System.out.println("I was here");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("I was here too");
        return "Logout";
    }
}
