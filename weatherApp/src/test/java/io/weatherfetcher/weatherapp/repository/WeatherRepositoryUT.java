package io.weatherfetcher.weatherapp.repository;

import io.weatherfetcher.weatherapp.model.WeatherData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class WeatherRepositoryUT {
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    void testFindByCity(){
        WeatherData weatherData=WeatherData.builder()
                .city("cbe")
                .temperature(43.9)
                .humidity(21)
                .build();
        weatherRepository.save(weatherData);
        WeatherData weatherData1= weatherRepository.findByCity("cbe");
        assertEquals("cbe",weatherData1.getCity());
        assertEquals(43.9,weatherData1.getTemperature());
        assertEquals(21,weatherData1.getHumidity());
    }
}
