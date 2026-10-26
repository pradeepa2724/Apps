package io.weatherfetcher.weatherapp.controller;

import io.weatherfetcher.weatherapp.dto.WeatherRequestDTO;
import io.weatherfetcher.weatherapp.dto.WeatherResponseDTO;
import io.weatherfetcher.weatherapp.model.WeatherDTO;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.service.WeatherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    @GetMapping
    public Page<WeatherData> getAllData(@RequestParam int page,@RequestParam int size){
        return weatherService.getAllData(page,size);
    }
    public WeatherController(WeatherService weatherService) {
        this.weatherService=weatherService;
    }
    @GetMapping("/{city}")
    public WeatherDTO fetchByCity(@NotBlank @PathVariable String city){
        WeatherData data=weatherService.fetchByCity(city);
        return new WeatherDTO(data);
    }
    @PostMapping
    public WeatherResponseDTO saveWeather(@Valid @RequestBody WeatherRequestDTO  requestDTO){
        return weatherService.saveWeather(requestDTO);
    }
    @PutMapping("/{id}")
    public WeatherData updateById(@PathVariable Long id, @Valid @RequestBody WeatherData weatherData){
        return weatherService.updateWeather(id,weatherData);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@Min(1) @PathVariable Long id){
         weatherService.deleteById(id);
    }

}
