package io.weatherfetcher.weatherapp.service;

import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    public WeatherService(WeatherRepository weatherRepository){
        this.weatherRepository=weatherRepository;
    }
    public WeatherData fetchByCity(String city){
        return weatherRepository.findByCity(city);
    }
    public WeatherData saveWeather(WeatherData weatherData) {
        return weatherRepository.save(weatherData);
    }
}
