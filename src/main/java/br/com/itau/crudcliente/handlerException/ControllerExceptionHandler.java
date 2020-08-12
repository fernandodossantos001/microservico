package br.com.itau.crudcliente.handlerException;


import br.com.itau.crudcliente.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest){
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage(), Calendar.getInstance());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exception(Exception exception, HttpServletRequest httpServletRequest){
        StandardError standardError = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), Calendar.getInstance());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardError);
    }
}
