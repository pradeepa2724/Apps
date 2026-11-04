package io.weatherfetcher.weatherapp;

import io.weatherfetcher.weatherapp.security.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherappApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(WeatherappApplication.class, args);
		JwtUtil jwtUtil=new JwtUtil();
		String token = jwtUtil.generateToken("testuser");
		System.out.println(token);
		String extractedUsername= jwtUtil.extractUserName(token);
		System.out.println(extractedUsername);
		System.out.println(jwtUtil.isTokenValid(token,extractedUsername));
	}
}
