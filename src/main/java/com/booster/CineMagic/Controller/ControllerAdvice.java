package com.booster.CineMagic.Controller;

import com.booster.CineMagic.Exception.EmptyDataListException;
import com.booster.CineMagic.Exception.EmptyListException;
import com.booster.CineMagic.Exception.ExistingDataException;
import com.booster.CineMagic.Exception.NotFoundExceptionCinema;
import com.booster.CineMagic.Util.ResponseError;
import com.booster.CineMagic.Util.ResponseErrorList;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = NotFoundExceptionCinema.class)
    public ResponseEntity<ResponseError> handleNotFoundExceptionCinema(NotFoundExceptionCinema exceptionCinema){
        ResponseError error = new ResponseError(exceptionCinema.getErrorCode(), exceptionCinema.getMessage());

        return new ResponseEntity<>(error, exceptionCinema.getErrorStatus());
    }

    @ExceptionHandler(value = EmptyListException.class)
    public ResponseEntity<ResponseError> handleEmptyListException(EmptyListException exception){
        ResponseError error = new ResponseError(exception.getErrorCode(), exception.getMessage());

        return new ResponseEntity<>(error, exception.getErrorStatus());
    }

    @ExceptionHandler(value = EmptyDataListException.class)
    public ResponseEntity<ResponseErrorList> handleEmptyDataListException(EmptyDataListException exception){
        List<String> errors = exception.getErrorResults().getFieldErrors().stream()
                                .map(FieldError::getDefaultMessage).collect(Collectors.toList());

        ResponseErrorList errorList = new ResponseErrorList(exception.getErrorCode(), exception.getMessage(), errors);

        return new ResponseEntity<>(errorList, exception.getErrorStatus());
    }

    @ExceptionHandler(value = ExistingDataException.class)
    public ResponseEntity<ResponseError> handleExistingDataException(ExistingDataException exception){
        ResponseError error = new ResponseError(exception.getErrorCode(), exception.getMessage());

        return new ResponseEntity<>(error, exception.getErrorStatus());
    }

}
