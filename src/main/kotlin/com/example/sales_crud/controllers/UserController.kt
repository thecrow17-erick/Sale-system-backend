package com.example.sales_crud.controllers

import com.example.sales_crud.dto.response.ApiResponse
import com.example.sales_crud.dto.user.CreateUserDto
import com.example.sales_crud.dto.user.UserResponse
import com.example.sales_crud.model.User
import com.example.sales_crud.services.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID
import kotlin.contracts.Returns

@RestController
@RequestMapping("user")
class UserController (
    @Autowired private val userService: UserService
) {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam search: String?,
        request: HttpServletRequest
    ): ApiResponse<Page<User>> {
        val username = request.getAttribute("user_username") as String;
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            message = "Todos los usuarios",
            data = this.userService.findAllUsers(search, page, size,username)
        )
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(@PathVariable id: UUID): ApiResponse<UserResponse> {
        return ApiResponse(
            statusCode = HttpStatus.OK.value(),
            message = "El usuario",
            data = UserResponse(this.userService.findUserId(id))
        )
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createdUser(
        @RequestBody @Valid createUserDto: CreateUserDto
    ): ApiResponse<UserResponse> {
        return ApiResponse(
            statusCode = HttpStatus.CREATED.value(),
            message = "Usuario creado",
            data = UserResponse(this.userService.createUser(createUserDto))
        )
    }
}