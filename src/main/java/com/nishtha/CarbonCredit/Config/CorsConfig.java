//package com.nishtha.CarbonCredit.Config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.*;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//
//@Configuration
//public class CorsConfig {
//
//    @Value("${app.cors.allowed-origins}")
//    private String allowedOriginsCsv;
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        Arrays.stream(allowedOriginsCsv.split(",")).forEach(config::addAllowedOriginPattern);
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//}

