package io.weatherfetcher.weatherapp.service;

import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceUT {
    @Mock
    private WeatherRepository weatherRepository;
    @InjectMocks
    private WeatherService weatherService;

    @Test
    void testFetchByCity(){
        WeatherData weatherData=new WeatherData().builder()
                        .city("cbe")
                        .temperature(43.9)
                        .humidity(21)
                        .build();
        when(weatherRepository.findByCity("cbe")).thenReturn(weatherData);
        WeatherData weatherData1=weatherService.fetchByCity("cbe");
        assertEquals("cbe",weatherData1.getCity());
        assertEquals(43.9,weatherData1.getTemperature());
        assertEquals(21,weatherData1.getHumidity());
        verify(weatherRepository).findByCity("cbe");
    }
    @Test
    void testSaveWeather(){
        WeatherData weatherData=new WeatherData().builder()
                .city("cbe")
                .temperature(43.9)
                .humidity(21)
                .build();
        when(weatherRepository.save(weatherData)).thenReturn(weatherData);
        WeatherData weatherData1= weatherService.saveWeather(weatherData);
        assertEquals("cbe",weatherData1.getCity());
        assertEquals(43.9,weatherData1.getTemperature());
        assertEquals(21,weatherData1.getHumidity());
        verify(weatherRepository).save(weatherData);
    }
    @Test
    void testUpdateWeather(){
        WeatherData existingData=new WeatherData().builder()
                .city("cbe")
                .temperature(43.9)
                .humidity(21)
                .build();
        WeatherData updatedData= new WeatherData().builder()
                        .city("selam")
                        .temperature(32.0)
                        .humidity(41)
                        .build();
        when(weatherRepository.findById(1L)).thenReturn(Optional.ofNullable(existingData));
        when(weatherRepository.save(updatedData)).thenReturn(updatedData);
        WeatherData weatherData1= weatherService.updateWeather(1L,updatedData);
        assertEquals("selam",weatherData1.getCity());
        assertEquals(32.0,weatherData1.getTemperature());
        assertEquals(41,weatherData1.getHumidity());
        verify(weatherRepository).save(updatedData);
    }
    @Test
    void testDeletedById(){
        weatherService.deleteById(1L);
        verify(weatherRepository).deleteById(1L);
    }
}
