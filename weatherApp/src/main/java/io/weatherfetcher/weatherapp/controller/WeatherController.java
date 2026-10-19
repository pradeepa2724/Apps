package io.weatherfetcher.weatherapp.controller;

import io.weatherfetcher.weatherapp.model.WeatherDTO;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService=weatherService;
    }
    @GetMapping("/{city}")
    public WeatherDTO getCity(@PathVariable String city){
        WeatherData data=weatherService.fetchByCity(city);
        return new WeatherDTO(data);
    }
    @PostMapping
    public WeatherData saveWeather(@RequestBody WeatherData weatherData){
        return weatherService.saveWeather(weatherData);
    }

}
