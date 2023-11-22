package com.booster.CineMagic.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyListException extends RuntimeException{
    public EmptyListException(String exceptionMessage){
        super("Empty List - " + exceptionMessage);
    }

}
