package com.example.sales_crud.dto.user

import com.example.sales_crud.model.Role
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import lombok.Data

@Data
data class CreateUserDto(
    @NotNull
    @Size(min = 3, max = 50, message = "Minimo 3 caracteres, maximo 50 para el nombre")
    val username: String,
    @NotNull
    @Size(min = 5, message = "minimo de 5 caracteres para el password")
    val password: String,
    @NotNull
    val role: Long
);
