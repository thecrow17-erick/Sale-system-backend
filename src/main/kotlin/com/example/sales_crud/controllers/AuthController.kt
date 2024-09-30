package com.example.sales_crud.controllers

import com.example.sales_crud.dto.auth.LoginDto
import com.example.sales_crud.dto.auth.LoginResponse
import com.example.sales_crud.dto.response.ApiResponse
import com.example.sales_crud.services.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController()
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {


    @PostMapping( "sign-in")
    @ResponseStatus(HttpStatus.OK)
    fun loginController(@Valid @RequestBody loginDto: LoginDto):ApiResponse<LoginResponse>{
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            message = "Loggin accept",
            data = this.authService.login(loginDto)
        );
    }

}