package io.weatherfetcher.weatherapp.service;

import io.weatherfetcher.weatherapp.exception.WeatherNotFoundException;
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

    public WeatherData updateWeather(Long id,WeatherData weatherData) {
        WeatherData existingData=weatherRepository.findById(id).orElseThrow(()-> new WeatherNotFoundException("Weather data not found with id : "+id));
        existingData.setCity(weatherData.getCity());
        existingData.setHumidity(weatherData.getHumidity());
        existingData.setTemperature(weatherData.getTemperature());
        return weatherRepository.save(existingData);
    }

    public void deleteById(Long id) {
        weatherRepository.deleteById(id);
    }
}
