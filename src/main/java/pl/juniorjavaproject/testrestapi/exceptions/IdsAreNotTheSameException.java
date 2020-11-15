package pl.juniorjavaproject.testrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IdsAreNotTheSameException extends RuntimeException {
    public IdsAreNotTheSameException(String message) {
        super(message);
    }
}
