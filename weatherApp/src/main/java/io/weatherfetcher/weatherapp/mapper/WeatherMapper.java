package io.weatherfetcher.weatherapp.mapper;
import io.weatherfetcher.weatherapp.dto.WeatherRequestDTO;
import io.weatherfetcher.weatherapp.dto.WeatherResponseDTO;
import io.weatherfetcher.weatherapp.model.WeatherData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    @Mapping(source = "city", target = "city")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "humidity", target = "humidity")
    WeatherData toEntity(WeatherRequestDTO weatherRequestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "humidity", target = "humidity")
    WeatherResponseDTO toResponseDTO(WeatherData weatherData);
}
