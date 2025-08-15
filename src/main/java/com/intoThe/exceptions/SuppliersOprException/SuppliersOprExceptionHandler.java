package com.intoThe.exceptions.SuppliersOprException;

import com.intoThe.errorResponse.SuppliersExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuppliersOprExceptionHandler extends RuntimeException{
    String message;
    public SuppliersOprExceptionHandler(String message){
        super(message);
        this.message = message;
    }

    /**
     * This method handles the case when a supplier is not found.
     * It creates a {@link SuppliersExceptionResponse} object with the appropriate
     * HTTP status code and error message.
     *
     * @return a {@link ResponseEntity} object containing the {@link SuppliersExceptionResponse}
     * object and the HTTP status code {@link HttpStatus#NOT_FOUND}.
     */
    public ResponseEntity<SuppliersExceptionResponse> supplierNotFound(){
        SuppliersExceptionResponse suppliersExceptionResponse
                = new SuppliersExceptionResponse(HttpStatus.NOT_FOUND.value(),
                this.message);
        return new ResponseEntity<>(suppliersExceptionResponse,HttpStatus.NOT_FOUND);
    }
}
