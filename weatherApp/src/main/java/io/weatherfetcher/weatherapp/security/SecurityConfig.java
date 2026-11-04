package io.weatherfetcher.weatherapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        System.out.println(">>> CUSTOM SECURITY CONFIG LOADED <<<");
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs/swagger-config"
                        ).permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.disable())
                .build();

    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails=User
                .withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
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
