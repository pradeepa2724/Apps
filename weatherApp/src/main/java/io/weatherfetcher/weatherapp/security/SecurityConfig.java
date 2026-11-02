package io.weatherfetcher.weatherapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        System.out.println(">>> CUSTOM SECURITY CONFIG LOADED <<<");
        return httpSecurity
                .authorizeHttpRequests(auth->auth.requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs/swagger-config"
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();

    }
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
//        throws Exception {
//
//    return httpSecurity
//            .authorizeHttpRequests(auth ->
//                    auth.anyRequest().permitAll()
//            )
//            .build();
//}
}
