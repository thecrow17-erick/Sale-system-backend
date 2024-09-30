package com.example.sales_crud.configuration

import com.example.sales_crud.security.JwtRequestFilter
import lombok.AllArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@AllArgsConstructor
class WebSecurityConfiguration(
    private val jwtRequestFilter: JwtRequestFilter
){
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests {
                auth ->
                    auth
                        .requestMatchers(HttpMethod.GET,"/user/**").hasAnyAuthority("get orders")
                        .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
            }
            .sessionManagement{ session ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .csrf{csrf -> csrf.disable()}

        return http.build();
    }
}