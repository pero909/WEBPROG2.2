package mk.finki.ukim.mk.lab.model.Exceptions;

public class UserNameExistsException extends RuntimeException{
    public UserNameExistsException(String username) {
        super(String.format("User with username:%s already exists",username));
    }
}
