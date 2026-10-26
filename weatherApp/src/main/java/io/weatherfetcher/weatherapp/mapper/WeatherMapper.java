package io.weatherfetcher.weatherapp.mapper;
import io.weatherfetcher.weatherapp.dto.WeatherRequestDTO;
import io.weatherfetcher.weatherapp.dto.WeatherResponseDTO;
import io.weatherfetcher.weatherapp.model.WeatherData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherData toEntity(WeatherRequestDTO weatherRequestDTO);
    WeatherResponseDTO toResponseDTO(WeatherData weatherData);
}
