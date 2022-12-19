package mk.finki.ukim.mk.lab.service.imp;



import mk.finki.ukim.mk.lab.model.Enum.Role;
import mk.finki.ukim.mk.lab.model.Exceptions.UserNameExistsException;
import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.Exceptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullName;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User login(String username, String password) {
        if(username==null||username.isEmpty()||password==null||password.isEmpty()){
            throw new InvalidArgumentException();
        }

        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidArgumentException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname,Role role) {
        if(username==null||username.isEmpty()
                ||password==null||password.isEmpty()
                ||repeatPassword==null||repeatPassword.isEmpty()
        ) throw new InvalidArgumentException();

        if(userRepository.findByUsername(username).isPresent()){
            throw new UserNameExistsException(username);
        }
        if(!password.equals(repeatPassword)){
            throw new InvalidArgumentException();
        }

        User user=new User(username,new UserFullName(name,surname),password,role);

        this.userRepository.save(user);
        return user;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));
    }


}
