package virement.international.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExceptionHandler
{
    private static final String SERVER_SIDE_ERROR = "Server side error";

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason=SERVER_SIDE_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void runtimeException() {}

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason=SERVER_SIDE_ERROR)
    @ExceptionHandler(Exception.class)
    public void exception() {}


}
