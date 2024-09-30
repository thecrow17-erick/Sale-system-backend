package com.example.sales_crud.configuration

import lombok.AllArgsConstructor
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableWebSecurity
@AllArgsConstructor
class WebSecurityConfiguration(
//    private val jwtRequestFilter: Jwt
){
}