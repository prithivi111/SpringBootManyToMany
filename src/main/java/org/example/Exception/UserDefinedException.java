package org.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserDefinedException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IdNotFoundException.class)
    public ResponseEntity<Object> idNotMatch(IdNotFoundException e){

        return new ResponseEntity<>(e.customMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> studentNotFoundMethod(StudentNotFoundException e){

        return new ResponseEntity<>(e.customMessage(), HttpStatus.NOT_FOUND);
    }
}
