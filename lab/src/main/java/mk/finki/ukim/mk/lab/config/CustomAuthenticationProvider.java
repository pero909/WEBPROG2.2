package mk.finki.ukim.mk.lab.config;

import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if("".equals(username)||"".equals(password)){
            throw new BadCredentialsException("Invalid Credentials");
        }
        UserDetails userDetails = this.authService.loadUserByUsername(username);

        if(!userDetails.getPassword().equals(password)){
            throw  new BadCredentialsException("Invalid Credentials");
        }
        return new UsernamePasswordAuthenticationToken(userDetails
                ,userDetails.getPassword()
                ,userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationProvider.class);
    }
}
