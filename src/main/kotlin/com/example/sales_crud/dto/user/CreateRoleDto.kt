package com.example.sales_crud.dto.user

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateRoleDto(
    @NotNull
    @Size(min = 3, max = 50, message = "Minimo 3 caracteres, maximo 50 para el nombre")
    val name : String,
    @NotNull
    @Size(min = 3, max = 255, message = "Minimo 3 caracteres, maximo 255 para la descripcion")
    val description: String
)
