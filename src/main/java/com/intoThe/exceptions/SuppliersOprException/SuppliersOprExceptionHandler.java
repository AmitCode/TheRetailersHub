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

    public ResponseEntity<SuppliersExceptionResponse> supplierNotFound(){
        SuppliersExceptionResponse suppliersExceptionResponse
                = new SuppliersExceptionResponse(HttpStatus.NOT_FOUND.value(),
                this.message);
        return new ResponseEntity<>(suppliersExceptionResponse,HttpStatus.NOT_FOUND);
    }
}
