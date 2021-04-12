package virement.international.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
public class BadRequestException extends RuntimeException {


    private static final long serialVersionUID = -5375193388994476417L;

    public BadRequestException() {
        super("Invalid query");
    }

    public BadRequestException(String msg) {
        super(msg);
    }

}
