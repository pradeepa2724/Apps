package io.weatherfetcher.weatherapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerUT {
    @MockitoBean
    private WeatherService weatherService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testFetchByCity() throws Exception {
        WeatherData data=new WeatherData().builder()
                        .city("cbe").temperature(43.9).humidity(21).build();
        when(weatherService.fetchByCity("cbe")).thenReturn(data);
        mockMvc.perform(get("/weather/cbe"))
                .andExpect(jsonPath("$.city").value("cbe"))
                .andExpect(jsonPath("$.temperature").value(43.9))
                .andExpect(jsonPath("$.humidity").value(21))
                .andExpect(status().isOk());
        verify(weatherService).fetchByCity("cbe");
    }
    @Test
    void testSaveWeather() throws Exception{
        WeatherData weatherData=new WeatherData().builder()
                .city("cbe").temperature(43.9).humidity(21).build();
        when(weatherService.saveWeather(weatherData)).thenReturn(weatherData);
        mockMvc.perform(post("/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(weatherData)))
                .andExpect(jsonPath("$.city").value("cbe"))
                .andExpect(jsonPath("$.temperature").value(43.9))
                .andExpect(jsonPath("$.humidity").value(21))
                .andExpect(status().isOk());
        verify(weatherService).saveWeather(weatherData);
    }
    @Test
    void testUpdateById() throws Exception {
        WeatherData weatherData=new WeatherData().builder()
                .city("cbe")
                .temperature(43.9)
                .humidity(21)
                .build();
        when(weatherService.updateWeather(1L,weatherData)).thenReturn(weatherData);
        mockMvc.perform(put("/weather/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(weatherData)))
                .andExpect(jsonPath("$.city").value("cbe"))
                .andExpect(jsonPath("$.temperature").value(43.9))
                .andExpect(jsonPath("$.humidity").value(21))
                .andExpect(status().isOk());
        verify(weatherService).updateWeather(1L,weatherData);
    }
    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/weather/1"))
                .andExpect(status().isOk());
        verify(weatherService).deleteById(1L);
    }
}
