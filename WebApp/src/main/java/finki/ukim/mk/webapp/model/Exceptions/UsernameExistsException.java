package finki.ukim.mk.webapp.model.Exceptions;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String username){
        super(String.format("Username %s already exists",username));
    }
}
