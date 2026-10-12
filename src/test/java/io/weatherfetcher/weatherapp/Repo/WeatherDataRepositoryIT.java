package io.weatherfetcher.weatherapp.Repo;

import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(properties = "spring.config.name=application")
@TestPropertySource("classpath:application.properties")
class WeatherDataRepositoryIT {
    @Autowired
    public WeatherRepository weatherRepository;

    @Test
    void saveAndFindCity(){
        WeatherData weatherData=new WeatherData();
        weatherData.setCity("Mumbai");
        weatherData.setTemperature(23.2);
        weatherRepository.save(weatherData);
        List<WeatherData> weatherDataList= weatherRepository.findByCity("Mumbai");
        assertFalse(weatherDataList.isEmpty());
        assertEquals("Mumbai",weatherDataList.get(0).getCity());
    }
}
