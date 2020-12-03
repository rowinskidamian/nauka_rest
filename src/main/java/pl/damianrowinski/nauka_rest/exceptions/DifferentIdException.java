package pl.damianrowinski.nauka_rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DifferentIdException extends RuntimeException {
    public DifferentIdException(String message) {
        super(message);
    }
}
