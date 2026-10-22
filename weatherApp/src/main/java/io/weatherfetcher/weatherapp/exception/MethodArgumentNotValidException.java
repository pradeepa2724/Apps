package io.weatherfetcher.weatherapp.exception;

public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
