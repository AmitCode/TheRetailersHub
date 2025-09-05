package com.intoThe.exceptions;

import com.intoThe.errorResponse.RetailerExceptionResponse;
import com.intoThe.exceptions.SuppliersOprException.SuppliersOprExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalLevelException extends Exception{


    /**
     * Handles {@link SuppliersOprExceptionHandler} exceptions, returning a
     * {@link ResponseEntity} with a {@link SuppliersExceptionResponse} object.
     *
     * @param suppliersOprExceptionHandler the exception to be handled
     * @return a ResponseEntity with a SuppliersExceptionResponse object
     */

    //@//ExceptionHandler(SuppliersOprExceptionHandler.class)
//    public ResponseEntity<SuppliersExceptionResponse> supplierNotFoundHandler(SuppliersOprExceptionHandler suppliersOprExceptionHandler){
//        return suppliersOprExceptionHandler.supplierNotFound();
//
//    }
}
