package apc.entjava.agora;

import apc.entjava.agora.dataobjects.CreateUserDao;
import apc.entjava.agora.services.CreateUserService;
import sun.security.validator.ValidatorException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
public class CreateUserBean {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

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

    public void validatePassword(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? ""
                : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        // get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("password2");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {

            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();

        }

        if (password.length()<8){

            FacesMessage msg = new FacesMessage("Password must have at least 8 letters");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();

        }
    }

    public void validateUsername(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get password
        UIInput uiInputUsername = (UIInput) components.findComponent("username");
        String username = uiInputUsername.getLocalValue() == null ? ""
                : uiInputUsername.getLocalValue().toString();
        String usernameId = uiInputUsername.getClientId();

        // Let required="true" do its job.
        if (username.isEmpty()) {
            return;
        }

        if (createUserService.duplicateUsername(username)) {

            FacesMessage msg = new FacesMessage("This username is already taken");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(usernameId, msg);
            fc.renderResponse();

        }

    }

    private CreateUserService createUserService = new CreateUserDao();

    public String createUser() {
        if (createUserService.createUser(firstName, lastName, username, email, password)) {
            return "createUserSuccess";
        } else {
            return "error";
        }
    }


}
