package io.weatherfetcher.weatherapp.repository;

import io.weatherfetcher.weatherapp.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository <WeatherData, Long>{
}
