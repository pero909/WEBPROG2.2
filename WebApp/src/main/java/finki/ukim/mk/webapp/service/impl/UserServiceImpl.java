package finki.ukim.mk.webapp.service.impl;

import finki.ukim.mk.webapp.model.Exceptions.InvalidArgumentsException;
import finki.ukim.mk.webapp.model.Exceptions.PasswordsDontMatchException;
import finki.ukim.mk.webapp.model.Exceptions.UsernameExistsException;
import finki.ukim.mk.webapp.model.Role;
import finki.ukim.mk.webapp.model.User;
import finki.ukim.mk.webapp.repository.jpa.UserRepository;
import finki.ukim.mk.webapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword,
                         String name, String surname, Role role) {

            if(username==null||username.isEmpty()
                    ||password==null||password.isEmpty()
                    ||repeatPassword==null||repeatPassword.isEmpty()
            ) throw new InvalidArgumentsException();


            if(!password.equals(repeatPassword)){
                throw new PasswordsDontMatchException();
            }
            if(this.userRepository.findByUsername(username).isPresent()) {
                throw new UsernameExistsException(username);
            }

            User user=new User(username,passwordEncoder.encode(password),name,surname,role);
            return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException(username));
    }
}
