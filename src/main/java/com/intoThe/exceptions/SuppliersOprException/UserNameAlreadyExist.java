package com.intoThe.exceptions.SuppliersOprException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameAlreadyExist extends Exception{
    String exceptionMessage;
    public UserNameAlreadyExist(String message){
        super(message);
        this.exceptionMessage = message;
    }
}
