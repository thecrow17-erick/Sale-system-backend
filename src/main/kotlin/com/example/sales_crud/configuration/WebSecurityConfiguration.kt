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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
@EnableWebSecurity
@AllArgsConstructor
class WebSecurityConfiguration(
    private val jwtRequestFilter: JwtRequestFilter
){
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors {  }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests {
                auth ->
                    auth
                        .requestMatchers(HttpMethod.GET,"/user/**").hasAnyAuthority("ver usuario")
                        .requestMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("crear usuario")
                        .requestMatchers(HttpMethod.GET,"/category/**").hasAnyAuthority("ver categoria")
                        .requestMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("crear categoria")
                        .requestMatchers(HttpMethod.PATCH, "/category/**").hasAnyAuthority("editar categoria")
                        .requestMatchers(HttpMethod.DELETE, "/category/**").hasAnyAuthority("eliminar categoria")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasAnyAuthority("crear producto")
                        .requestMatchers(HttpMethod.GET, "/product/**").hasAnyAuthority("ver producto")
                        .requestMatchers(HttpMethod.PATCH, "/product/**").hasAnyAuthority("editar producto")
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasAnyAuthority("eliminar producto")
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