package com.app.cloudStorage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;
import java.util.List;

@Configuration
public class CorsConfig {

   @Bean
   org.springframework.web.filter.CorsFilter corsFilter() {
       var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
       var globalCorsConfiguration = new CorsConfiguration();

       globalCorsConfiguration.addAllowedOrigin("http://localhost:3000");
       globalCorsConfiguration.addAllowedHeader("*");
       globalCorsConfiguration.setAllowCredentials(true);
       globalCorsConfiguration.setAllowedMethods(List.of(
               HttpMethod.GET.name(),
               HttpMethod.DELETE.name(),
               HttpMethod.PUT.name(),
               HttpMethod.POST.name(),
               HttpMethod.PATCH.name()
       ));
       globalCorsConfiguration.setMaxAge(Duration.ofSeconds(10));

       corsConfigurationSource.registerCorsConfiguration("/**", globalCorsConfiguration);
       return new CorsFilter(corsConfigurationSource);
   }
}
