package io.weatherfetcher.weatherapp.Repo;

import io.weatherfetcher.weatherapp.model.WeatherData;
import io.weatherfetcher.weatherapp.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

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
    @Test
    void testGetWeatherByCity() throws Exception {
        WeatherData data= new WeatherData();
        data.setCity("Mumbai");
        data.setTemperature(20.0);
        data.setHumidity(10);
        weatherRepository.save(data);

        mockMvc.perform(get("/weather/mumbai"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.city").value("Mumbai"))
                .andExpect((ResultMatcher) jsonPath("$.temperature").value(30.5))
                .andExpect((ResultMatcher) jsonPath("$.humidity").value(70));    }
}
