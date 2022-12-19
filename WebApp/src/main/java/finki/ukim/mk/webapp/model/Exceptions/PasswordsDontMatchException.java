package finki.ukim.mk.webapp.model.Exceptions;

public class PasswordsDontMatchException extends RuntimeException {
    public PasswordsDontMatchException(){
        super("Passwords Dont match");
    }
}
