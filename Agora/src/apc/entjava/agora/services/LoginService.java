package apc.entjava.agora.services;

public interface LoginService {
    boolean login(String username, String password);
    String nickname(String username, String password);
}
