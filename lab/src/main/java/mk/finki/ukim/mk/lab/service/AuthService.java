package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Enum.Role;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    User login(String username,String password);
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
