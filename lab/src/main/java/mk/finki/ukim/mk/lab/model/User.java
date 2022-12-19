package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import mk.finki.ukim.mk.lab.model.Enum.Role;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "balloon_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String username;
    // private String name;
    @Enumerated(value = EnumType.STRING )
    private mk.finki.ukim.mk.lab.model.Enum.Role role;
    @Convert(converter = UserFullnameConverter.class)
    private UserFullName userFullName;

    private String password;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User() {

    }


    public UserFullName getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(UserFullName userFullName) {
        this.userFullName = userFullName;
    }

//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user",fetch =FetchType.EAGER)
    private List<ShoppingCart> cart;

    public User(String username, UserFullName userFullName, String password, mk.finki.ukim.mk.lab.model.Enum.Role role) {

        this.username = username;
        this.password = password;
        this.userFullName=userFullName;
        this.dateOfBirth = LocalDate.now();
        this.role=role;

    }



    public User(String test1, String s, String test11, String s1, String test12, mk.finki.ukim.mk.lab.model.Enum.Role userRole) {

    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public mk.finki.ukim.mk.lab.model.Enum.Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}