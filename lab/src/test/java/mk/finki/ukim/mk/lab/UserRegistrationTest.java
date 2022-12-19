package mk.finki.ukim.mk.lab;


;
import mk.finki.ukim.mk.lab.model.Enum.Role;
import mk.finki.ukim.mk.lab.model.Exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.imp.AuthServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.MapKeyColumn;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthServiceImpl service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
         User user = new User("test1","test1","test1","test1","test1",Role.USER_ROLE);
        Mockito.when(this.userRepository.save(Mockito.any())).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service=Mockito.spy(new AuthServiceImpl(this.userRepository,this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister(){
        User user= this.service.register("test1","test1","test1",
                "test1","test1",Role.USER_ROLE);

        Mockito.verify(this.service).register("test1","test1","test1",
                "test1","test1",Role.USER_ROLE);

        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("role do not match",Role.USER_ROLE,user.getRole());
        Assert.assertEquals("usernames do not match","test1",user.getUsername());
        Assert.assertEquals("password do not match","test1",user.getPassword());
    }
    @Test
    public void testEmptyUsername(){
        String username="";
        Assert.assertThrows("InvalidArgumentExceptionExpected",
                InvalidArgumentException.class,
                ()->this.service.register(username,"password",
                        "password", "name", "surename"
                        ,Role.USER_ROLE));
        Mockito.verify(this.service).register(username,
                "password", "password",
                "name", "surename",Role.USER_ROLE );
    }
    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidArgumentExceptionExpected",
                InvalidArgumentException.class,
                ()->this.service.register(null,"password",
                        "password", "name", "surename"
                        ,Role.USER_ROLE));
        Mockito.verify(this.service).register(null,
                "password", "password",
                "name", "surename",Role.USER_ROLE );
    }
    @Test
    public void testEmptyPassword(){
        String username="username1";
        String password="";
        Assert.assertThrows("InvalidArgumentExceptionExpected",
                InvalidArgumentException.class,
                ()->this.service.register(username,password,
                        "password", "name", "surename"
                        ,Role.USER_ROLE));
        Mockito.verify(this.service).register(username,
                password, "password",
                "name", "surename",Role.USER_ROLE );
    }
    @Test
    public void testNullPassword(){
        String username="username1";
        String password=null;
        Assert.assertThrows("InvalidArgumentExceptionExpected",
                InvalidArgumentException.class,
                ()->this.service.register(username,password,
                        "password", "name", "surename"
                        ,Role.USER_ROLE));
        Mockito.verify(this.service).register(username,
                password, "password",
                "name", "surename",Role.USER_ROLE );
    }
    @Test
    public void testPasswordMismatch(){
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("InvalidArgumentExceptionExpected",
                InvalidArgumentException.class,
                ()->this.service.register(username,password,
                        confirmPassword, "name", "surename"
                        ,Role.USER_ROLE));
        Mockito.verify(this.service).register(username,
                password, confirmPassword,
                "name", "surename",Role.USER_ROLE );
    }
}
