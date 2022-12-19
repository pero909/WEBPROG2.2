package finki.ukim.mk.webapp.service;

import finki.ukim.mk.webapp.model.Role;
import finki.ukim.mk.webapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name
            , String surname, Role role);
}
