package io.weatherfetcher.weatherapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="weather_data")
public class WeatherData {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private Double temperature;
    private int humidity;
    private LocalDateTime fetchedAt;
    @Column(name="fetched_at")
    private String description;
}
