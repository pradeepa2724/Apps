package io.weatherfetcher.weatherapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name="weather_data")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String city;
    private String country;
    @NotNull
    private Double temperature;
    @NotNull
    private int humidity;
    @Column(name="fetched_at")
    private LocalDateTime fetchedAt;
    private String description;
}
