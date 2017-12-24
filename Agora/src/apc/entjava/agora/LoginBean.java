package apc.entjava.agora;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean {
    private String loginName;
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login1() {
        if(loginName.equals("test") && password.equals("me")){
            return "mainpage";
        }else{
            return "error";
        }
    }
}
