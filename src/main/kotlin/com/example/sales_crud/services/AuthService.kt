package com.example.sales_crud.services

import com.example.sales_crud.dto.auth.LoginDto
import com.example.sales_crud.dto.auth.LoginResponse
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.error.exception.UnauthorizedException
import com.example.sales_crud.model.User
import com.example.sales_crud.security.SecurityUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val userService: UserService,
    private val jwtService: JwtService,
) {

    fun login(loginDto: LoginDto):LoginResponse{
        val findUser = this.userService.findUser(loginDto.username);
        if(findUser.isEmpty){
            throw NotFoundException("User Not Found")
        }
        val valid = this.validatePassword(loginDto.password,findUser.get().password);

        if(!valid){
            throw UnauthorizedException("Invalid Password")
        }

        val userDetails: UserDetails = findUser.map { user: User -> SecurityUser(user) }.orElseThrow()
        val token = jwtService.getToken(userDetails)

        return LoginResponse(
            token = token,
            user = findUser.get()
        );
    }

    fun validatePassword(rawPassword: String, encodedPassword: String): Boolean {
        return BCryptPasswordEncoder().matches(rawPassword, encodedPassword)
    }

}