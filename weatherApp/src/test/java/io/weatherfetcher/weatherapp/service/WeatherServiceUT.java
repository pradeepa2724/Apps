package io.weatherfetcher.weatherapp.service;

import io.weatherfetcher.weatherapp.dto.WeatherRequestDTO;
import io.weatherfetcher.weatherapp.dto.WeatherResponseDTO;
import io.weatherfetcher.weatherapp.mapper.WeatherMapper;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
public class WeatherServiceUT {
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WeatherMapper weatherMapper;
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
        WeatherRequestDTO weatherRequestDTO=new WeatherRequestDTO().builder()
                .city("cbe").temperature(43.9).humidity(21).build();
        WeatherResponseDTO weatherResponseDTO=new WeatherResponseDTO().builder()
                .city("cbe").temperature(43.9).humidity(21).build();
        WeatherData weatherData=new WeatherData().builder()
                .city("cbe").temperature(43.9).humidity(21).build();
        when(weatherMapper.toEntity(weatherRequestDTO)).thenReturn(weatherData);
        when(weatherRepository.save(any(WeatherData.class))).thenReturn(weatherData);
        when(weatherMapper.toResponseDTO(any(WeatherData.class))).thenReturn(weatherResponseDTO);
        WeatherResponseDTO weatherData1= weatherService.saveWeather(weatherRequestDTO);
        assertEquals("cbe",weatherData1.getCity());
        assertEquals(43.9,weatherData1.getTemperature());
        assertEquals(21,weatherData1.getHumidity());
        verify(weatherMapper).toEntity(weatherRequestDTO);
        verify(weatherRepository).save(any(WeatherData.class));
        verify(weatherMapper).toResponseDTO(any(WeatherData.class));
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
