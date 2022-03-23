package br.com.verbososcorp.ilocation.exceptions;


import br.com.verbososcorp.ilocation.exceptions.exceptions.AuthExeption;
import br.com.verbososcorp.ilocation.exceptions.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExeptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourseNotFoundException(ResourceNotFoundException e, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthExeption.class)
    public ResponseEntity<ErrorMessage> authExeption(AuthExeption e, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                        WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getFieldErrors().get(0).getField() + " / "
                        + exception.getFieldErrors().get(0).getDefaultMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

}
