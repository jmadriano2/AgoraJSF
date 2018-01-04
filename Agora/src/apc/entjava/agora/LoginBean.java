package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CityDao;
import apc.entjava.agora.dataobjects.LoginDao;
import apc.entjava.agora.services.CityService;
import apc.entjava.agora.services.LoginService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class LoginBean {
    private String username;
    private String password;

    @ManagedProperty(value = "#{authBean}")
    private AuthBean authBean;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    private LoginService loginService = new LoginDao();
    private CityService cityService = new CityDao();

    public String login() {
        if (loginService.loginadmin(username,password)){
            authBean.setLoggedAdmin(loginService.loggedAdmin(username));
            authBean.setLoggedAdminname(authBean.getLoggedAdmin().getAdmin_nickname());
            return "Admin";
        }
        if(loginService.login(username,password)){
            authBean.setLoggedUser(loginService.loggedUser(username));
            authBean.setLoggedUsername(authBean.getLoggedUser().getUser_nickname());
            if (!cityService.userHasCity(username)){
                return "CityList";
            }
            return "Login";
        }else{
            return "error";
        }
    }
}
