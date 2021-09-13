package com.giovane.futebol.exceptions.handler;

import com.giovane.futebol.exceptions.notfound.ErrorDetails;
import com.giovane.futebol.exceptions.notfound.NotFoundDetails;
import com.giovane.futebol.exceptions.notfound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;
import java.util.*;

@ControllerAdvice
public class ExceptionHandlers extends Throwable{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException e){
        NotFoundDetails notFoundDetails;
        notFoundDetails = NotFoundDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .details(e.getMessage())
                .developerMessage("Id not found")
                .build();
        return new ResponseEntity<>(notFoundDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodNotValid(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(p -> errors.put(p.getField(), p.getDefaultMessage()));

        ErrorDetails notFoundDetails;
        notFoundDetails = ErrorDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Method not valid")
                .detailsErrors(errors)
                .developerMessage("Error! Field(s) cannot be null")
                .build();
        return new ResponseEntity<>(notFoundDetails, HttpStatus.BAD_REQUEST);
    }

}
