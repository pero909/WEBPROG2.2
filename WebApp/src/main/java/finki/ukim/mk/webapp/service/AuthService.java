package finki.ukim.mk.webapp.service;

import finki.ukim.mk.webapp.model.User;

public interface AuthService {
    User login(String username,String password);

}
