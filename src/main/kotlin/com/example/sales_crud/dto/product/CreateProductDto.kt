package com.example.sales_crud.dto.product

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.web.multipart.MultipartFile

data class CreateProductDto(
    @field:NotNull
    @field:Size(min = 1, max = 50)
    val name: String,
    @field:NotNull
    @field:DecimalMin(value = "5.00", inclusive = false)
    val price: Double,
    val description: String,
    val code: String,
    val category_id: Long,
    val image: MultipartFile
)
