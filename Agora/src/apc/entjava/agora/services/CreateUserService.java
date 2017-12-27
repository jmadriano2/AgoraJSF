package apc.entjava.agora.services;

public interface CreateUserService {
    boolean createUser(String fname, String lname, String username, String email, String password);
}
