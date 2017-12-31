package apc.entjava.agora;

import apc.entjava.agora.dataobjects.LoginDao;
import apc.entjava.agora.services.LoginService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class LoginBean {
    private String username;
    private String password;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private LoginService loginService = new LoginDao();

    public String login() {
        if(loginService.login(username,password)){
            user = loginService.queryUser(username);
            authBean.setLoggedUsername(user.getUser_nickname());
            return "Login";
        }else{
            return "error";
        }
    }

    public void loadUser() {
        //Look up User from database
        System.out.println("Loading User by Username: " + username);
        user = lookupUser(username);
    }
    public User lookupUser(String username) {
        //Use DAO to retrieve customer data
        //...
        return loginService.queryUser(username);
    }
}
