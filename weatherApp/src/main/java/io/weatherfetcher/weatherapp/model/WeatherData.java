package io.weatherfetcher.weatherapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
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
    private String city;
    private String country;
    private Double temperature;
    private int humidity;
    @Column(name="fetched_at")
    private LocalDateTime fetchedAt;
    private String description;
}
