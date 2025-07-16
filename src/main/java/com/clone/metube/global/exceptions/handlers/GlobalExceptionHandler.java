package com.clone.metube.global.exceptions.handlers;

import com.clone.metube.global.dtos.ErrorResponse;
import com.clone.metube.global.enums.ServerError;
import com.clone.metube.global.exceptions.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        ex.printStackTrace();

        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorCode = ServerError.INTERNAL_SERVER_ERROR.getErrorCode();
        var errorMessage = ServerError.INTERNAL_SERVER_ERROR.getMessage();

        var response = new ErrorResponse(errorCode, errorMessage);

        return ResponseEntity.status(statusCode).body(response);
    }
}
