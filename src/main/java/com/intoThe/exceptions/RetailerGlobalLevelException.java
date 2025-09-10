package com.intoThe.exceptions;

import com.intoThe.errorResponse.RetailerExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class RetailerGlobalLevelException{
//public class RetailerGlobalLevelException extends Exception{do not extends Exception in case of global exception handler.

    /**
     * This method is responsible for catching all unchecked exceptions and returning
     * a ResponseEntity containing a RetailerExceptionResponse object with the
     * HTTP status set to INTERNAL_SERVER_ERROR.
     *
     * @param exception The exception that was thrown.
     * @param webRequest The web request that triggered the exception.
     * @return A ResponseEntity object containing the RetailerExceptionResponse
     *         object and the HTTP status code INTERNAL_SERVER_ERROR.
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RetailerExceptionResponse> handleGlobalException(Exception exception,
                                                                           WebRequest webRequest) {
        return new ResponseEntity<>(new RetailerExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                webRequest.getDescription(false)),HttpStatus.INTERNAL_SERVER_ERROR);
    }

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * This method is responsible for catching all {@link HttpMessageNotReadableException}
     * and returning a ResponseEntity containing a RetailerExceptionResponse object with the
     * HTTP status set to NO_CONTENT.
     *
     * @param messageException The exception that was thrown.
     * @param webRequest The web request that triggered the exception.
     * @return A ResponseEntity object containing the RetailerExceptionResponse
     *         object and the HTTP status code NO_CONTENT.
     */
/* <<<<<<<<<<  387dbbe1-7a17-48df-8193-d06728791ddf  >>>>>>>>>>> */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RetailerExceptionResponse> handleRequestCannotBeEmpty(HttpMessageNotReadableException
                                                                                messageException,
                                                                                WebRequest webRequest){
        System.out.println("Inside Global Exception Handler");
        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.NO_CONTENT.value(),
                messageException.getMessage(),webRequest.getDescription(false)),
                HttpStatus.NO_CONTENT);
    }
}
