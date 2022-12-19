package mk.finki.ukim.mk.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             CustomAuthenticationProvider customAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/balloons/login","/balloons/register","/balloons/**"
                        ,"/BalloonOrder.do","/","/ConfirmationInfo","/selectBalloon").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/balloons/login").permitAll()
                .failureForwardUrl("/balloons/login?error=BadCredentials")
                .defaultSuccessUrl("/balloons/home",true)
                .and()
                .logout().logoutUrl("/balloons/logout")
                .deleteCookies("Balloon-Lab")
                .logoutSuccessUrl("/balloons/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.authenticationProvider(customAuthenticationProvider);
    }
}
