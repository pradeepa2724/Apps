package io.weatherfetcher.weatherapp.controller;

import io.weatherfetcher.weatherapp.model.WeatherDTO;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
