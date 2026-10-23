package io.weatherfetcher.weatherapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherRequestDTO {
//    @NotBlank
    private String city;
//    @NotNull
    private Double temperature;
//    @NotNull
    private Integer humidity;
}
