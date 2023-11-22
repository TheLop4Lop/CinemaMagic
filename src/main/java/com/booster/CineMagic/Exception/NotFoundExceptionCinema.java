package com.booster.CineMagic.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionCinema extends RuntimeException{
    public NotFoundExceptionCinema(String exceptionMessage){
        super("Not found - " + exceptionMessage);
    }

}
