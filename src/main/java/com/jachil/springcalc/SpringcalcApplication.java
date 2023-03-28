package com.jachil.springcalc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class SpringCalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.jachil.springcalc.SpringCalcApplication.class, args);
    }

    @GetMapping("/calculate")
    public CalculationResult calculate(
            @RequestParam(value = "num1") double num1,
            @RequestParam(value = "num2") double num2,
            @RequestParam(value = "operator") String operator) {

        double result = switch (operator) {
            case "add" -> num1 + num2;
            case "subtract" -> num1 - num2;
            case "multiply" -> num1 * num2;
            case "divide" -> num1 / num2;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };

        return new CalculationResult(result);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/calculate")
                        .allowedOrigins("*")
                        .allowedMethods("GET");
            }
        };
    }
    public record CalculationResult(double result) {}
}

