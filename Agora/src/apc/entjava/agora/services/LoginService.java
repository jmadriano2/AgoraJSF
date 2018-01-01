package apc.entjava.agora.services;

import apc.entjava.agora.objects.User;

public interface LoginService {
    boolean login(String username, String password);
    User loggedUser(String username);
}
