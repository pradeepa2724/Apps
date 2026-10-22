package io.weatherfetcher.weatherapp.exception;

public class WeatherNotFoundException extends RuntimeException {
    public WeatherNotFoundException(String message) {
        super(message);
    }
}
