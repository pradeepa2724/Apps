package io.weatherfetcher.weatherapp.service;

import io.weatherfetcher.weatherapp.dto.WeatherRequestDTO;
import io.weatherfetcher.weatherapp.dto.WeatherResponseDTO;
import io.weatherfetcher.weatherapp.exception.WeatherNotFoundException;
import io.weatherfetcher.weatherapp.mapper.WeatherMapper;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherService(WeatherRepository weatherRepository, WeatherMapper weatherMapper){
        this.weatherRepository=weatherRepository;
        this.weatherMapper = weatherMapper;
    }
    public WeatherData fetchByCity(String city){
        return weatherRepository.findByCity(city);
    }
    public WeatherResponseDTO saveWeather(WeatherRequestDTO requestDTO) {
//        WeatherData weatherData= weatherMapper.toEntity(requestDTO);
//        WeatherData saved=weatherRepository.save(weatherData);
//        log.info("Saved weatherdata inside the repository");
//        return weatherMapper.toResponseDTO(saved);

            System.out.println("DTO = " + requestDTO);

            WeatherData weatherData = weatherMapper.toEntity(requestDTO);

            System.out.println("ENTITY = " + weatherData);

            WeatherData saved = weatherRepository.save(weatherData);

            return weatherMapper.toResponseDTO(saved);
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

    public Page<WeatherData> getAllData(int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("id").descending());
        return weatherRepository.findAll(pageable);
    }
}
