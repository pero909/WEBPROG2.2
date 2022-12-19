package finki.ukim.mk.webapp.service.impl;

import finki.ukim.mk.webapp.model.Exceptions.InvalidArgumentsException;
import finki.ukim.mk.webapp.model.Exceptions.InvalidUserCredentialsException;
import finki.ukim.mk.webapp.model.Exceptions.PasswordsDontMatchException;
import finki.ukim.mk.webapp.model.Exceptions.UsernameExistsException;
import finki.ukim.mk.webapp.model.User;
import finki.ukim.mk.webapp.repository.impl.InMemoryUserRepository;
import finki.ukim.mk.webapp.repository.jpa.UserRepository;
import finki.ukim.mk.webapp.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User login(String username, String password) {
        if(username==null||username.isEmpty()||password==null||password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository
                .findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

}
