package finki.ukim.mk.webapp.repository.impl;

import finki.ukim.mk.webapp.bootstrap.DataHolder;
import finki.ukim.mk.webapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public Optional<User> findByUserName(String username){
        return DataHolder.users.stream().filter(u->u.getUsername().equals(username)).findFirst();
    }
    public Optional<User> findByUsernameAndPassword(String username,String password){
        return DataHolder.users.stream().filter(u->u.getUsername().equals(username)&&
                u.getPassword().equals(password)).findFirst();
    }

    public  User saveOrUpdate(User user){
        DataHolder.users.removeIf(r->r.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }

    public void delete(String username){
        DataHolder.users.removeIf(r->r.getUsername().equals(username));
    }
}
