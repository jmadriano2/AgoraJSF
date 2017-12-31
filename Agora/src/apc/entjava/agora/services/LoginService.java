package apc.entjava.agora.services;

import apc.entjava.agora.User;

public interface LoginService {
    boolean login(String username, String password);
    User queryUser(String username);
}
