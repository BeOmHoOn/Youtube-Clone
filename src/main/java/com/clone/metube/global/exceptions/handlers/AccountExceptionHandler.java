package com.clone.metube.global.exceptions.handlers;

import com.clone.metube.global.dtos.ErrorResponse;
import com.clone.metube.global.enums.AccountError;
import com.clone.metube.global.exceptions.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Object> handleAccountException(AccountException ex) {
        ex.printStackTrace();

        var statusCode = ex.getError().getHttpStatus();
        var errorCode = ex.getError().getErrorCode();
        var errorMessage = ex.getError().getMessage();

        var response = new ErrorResponse(errorCode, errorMessage);

        return ResponseEntity.status(statusCode).body(response);
    }
}
