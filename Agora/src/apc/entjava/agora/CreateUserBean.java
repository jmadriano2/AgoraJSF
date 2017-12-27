package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CreateUserDao;
import apc.entjava.agora.services.CreateUserService;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CreateUserBean {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String conpass;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }

    private CreateUserService createUserService = new CreateUserDao();

    public String createUser() {
        if(password.equals(conpass)){
            if(createUserService.createUser(firstName, lastName, username, email, password)){
                return "createUserSuccess";
            }else{
                return "error";
            }
        }
        return null;
    }
}
