package finki.ukim.mk.webapp.model.Exceptions;


public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException(){
        super("Invalid user credentials exception");
    }
}
