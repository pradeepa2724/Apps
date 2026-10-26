package io.weatherfetcher.weatherapp.integrationtest;

import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@Disabled
@SpringBootTest(properties = "spring.config.name=application")
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
class WeatherDataRepositoryIT {
    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public WeatherRepository weatherRepository;

//    @Test
//    void saveAndFindCity(){
//        WeatherData weatherData=new WeatherData();
//        weatherData.setCity("Mumbai");
//        weatherData.setTemperature(23.2);
//        weatherRepository.save(weatherData);
//        WeatherData weatherDataList= weatherRepository.findByCity("Mumbai");
//        assertNotNull(weatherDataList);
//        assertEquals("Mumbai",weatherData.getCity());
//    }
    @BeforeEach
    void setup(){
        weatherRepository.deleteAll();
    }
    @Test
    void testGetWeatherByCity() throws Exception {
        WeatherData data= new WeatherData();
        data.setCity("Mumbai");
        data.setTemperature(30.5);
        data.setHumidity(70);
        weatherRepository.save(data);
        System.out.println("COUNT : "+weatherRepository.count());
        System.out.println(weatherRepository.findAll());
        mockMvc.perform(get("/weather/Mumbai"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Mumbai"))
                .andExpect(jsonPath("$.temperature").value(30.5))
                .andExpect(jsonPath("$.humidity").value(70));
    }
}
