package io.weatherfetcher.weatherapp.controller;

import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void testGetMapping() throws Exception {
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
}
