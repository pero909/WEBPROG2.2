package mk.finki.ukim.mk.lab.model.Exceptions;

public class ColorNotChosenException extends RuntimeException{
    public ColorNotChosenException(){
        super("Please Choose a Color");
    }
}
