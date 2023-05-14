package com.asan.bank.exceptionhandler;

import com.asan.bank.exceptionhandler.exceptions.ExceptionResponse;
import com.asan.bank.exceptionhandler.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {



    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> getException(ServiceException serviceException) {

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(true);
        exceptionResponse.setMessage(serviceException.getErrorCode());

        return ResponseEntity.badRequest().body(exceptionResponse);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception) {

        ExceptionResponse value = new ExceptionResponse();
        value.setError(true);
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message =  fieldError.getDefaultMessage();
        value.setMessage(message);
        return ResponseEntity.badRequest().body(value);
    }
}
