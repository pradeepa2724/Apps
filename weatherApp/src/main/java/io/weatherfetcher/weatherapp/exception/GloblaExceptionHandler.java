package io.weatherfetcher.weatherapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GloblaExceptionHandler {
    @ExceptionHandler(WeatherNotFoundException.class)
    public ResponseEntity<String> handleWeatherNotFoundException(WeatherNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex){
        String message= ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}
