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
                        .requestMatchers(HttpMethod.GET,"/category/**").hasAnyAuthority("get category")
                        .requestMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("create category")
                        .requestMatchers(HttpMethod.PATCH, "/category/**").hasAnyAuthority("update category")
                        .requestMatchers(HttpMethod.DELETE, "/category/**").hasAnyAuthority("delete category")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasAnyAuthority("create product")
                        .requestMatchers(HttpMethod.GET, "/product/**").hasAnyAuthority("get products")
                        .requestMatchers(HttpMethod.PATCH, "/product/**").hasAnyAuthority("edit product")
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasAnyAuthority("delete product")
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