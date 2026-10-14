package io.weatherfetcher.weatherapp.model;


public class WeatherDTO {
    private String city;
    private Double temperature;
    private int humidity;

    public WeatherDTO(WeatherData weatherData) {
        this.city = weatherData.getCity();
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
    }

    public String getCity() {
        return city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }
}
