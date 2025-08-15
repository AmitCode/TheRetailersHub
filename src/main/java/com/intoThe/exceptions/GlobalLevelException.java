package com.intoThe.exceptions;

import com.intoThe.errorResponse.SuppliersExceptionResponse;
import com.intoThe.exceptions.SuppliersOprException.SuppliersOprExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalLevelException extends Exception{

    @ExceptionHandler(SuppliersOprExceptionHandler.class)
    public ResponseEntity<SuppliersExceptionResponse> supplierNotFoundHandler(SuppliersOprExceptionHandler suppliersOprExceptionHandler){
        return suppliersOprExceptionHandler.supplierNotFound();

    }
}
