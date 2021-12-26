package io.aanbuvenkatesh.weather.app.exception;

import io.aanbuvenkatesh.weather.exception.CityNotFoundException;
import io.aanbuvenkatesh.weather.exception.ServiceErrorException;
import io.aanbuvenkatesh.weather.exception.UnauthenticatedServiceException;
import io.aanbuvenkatesh.weather.view.PredictionError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class PredictionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public final ResponseEntity<PredictionError> handleCityNotFoundException(CityNotFoundException exception,
                                                                             HttpServletRequest request) {
        return new ResponseEntity(error(exception.getCode(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceErrorException.class)
    public final ResponseEntity<PredictionError> handleServiceErrorException(ServiceErrorException exception,
                                                                             HttpServletRequest request) {
        return new ResponseEntity(error(exception.getCode(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UnauthenticatedServiceException.class)
    public final ResponseEntity<PredictionError> handleUnauthenticatedServiceException(UnauthenticatedServiceException exception,
                                                                                       HttpServletRequest request) {
        return new ResponseEntity(error(exception.getCode(), exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<PredictionError> handleAllExceptions(Exception exception,
                                                                     HttpServletRequest request) {
        return new ResponseEntity(error("6000", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private PredictionError error(String code, String message) {
        return new PredictionError(code, message);
    }
}
