package com.example.sales_crud.dto.category

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import lombok.Data

@Data
data class CreateCategoryDto(
    @field:NotNull
    @field:Size(min = 5, max = 50, message = "Minimo 5 caracteres para el nombre")
    val name: String,
    @field:NotNull
    @field:Size(min = 10, max = 255, message = "Minimo 10 caracteres y un maximo de 255 caracteres")
    val description: String
)
