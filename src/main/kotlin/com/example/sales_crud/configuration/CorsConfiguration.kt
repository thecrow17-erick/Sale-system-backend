package com.example.sales_crud.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration: WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry): Unit {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedHeaders("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowCredentials(false)
    }
}