package pl.damianrowinski.nauka_rest.exceptions;

public class UserIdNotPresentException extends Exception {
    public UserIdNotPresentException(String message){
        super(message);
    }
}
