package io.weatherfetcher.weatherapp.controller;

import io.weatherfetcher.weatherapp.dto.AuthRequestDTO;
import io.weatherfetcher.weatherapp.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    public AuthController (JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.jwtUtil=jwtUtil;
        this.authenticationManager=authenticationManager;
    }
    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO authRequestDTO){
        System.out.println("LOGIN ENDPOINT HIT");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(),authRequestDTO.getPassword()));
        System.out.println("AUTH SUCCESS");
        return jwtUtil.generateToken(authRequestDTO.getUserName());
    }
    @GetMapping("/test")
    public String test(){
        System.out.println("TEST HIT");
        return "WORKING";
    }
}
