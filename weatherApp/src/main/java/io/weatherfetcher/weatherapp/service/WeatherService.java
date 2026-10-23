package io.weatherfetcher.weatherapp.service;

import io.weatherfetcher.weatherapp.dto.WeatherRequestDTO;
import io.weatherfetcher.weatherapp.dto.WeatherResponseDTO;
import io.weatherfetcher.weatherapp.exception.WeatherNotFoundException;
import io.weatherfetcher.weatherapp.mapper.WeatherMapper;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherService(WeatherRepository weatherRepository){
        this.weatherRepository=weatherRepository;
    }
    public WeatherData fetchByCity(String city){
        return weatherRepository.findByCity(city);
    }
    public WeatherResponseDTO saveWeather(WeatherRequestDTO requestDTO) {
        WeatherData weatherData= weatherMapper.toEntity(requestDTO);
        WeatherData saved=weatherRepository.save(weatherData);
        return weatherMapper.toResponseDto(saved);
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
