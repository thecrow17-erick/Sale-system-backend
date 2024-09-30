package com.example.sales_crud.error.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.http.HttpStatus

@Data
@AllArgsConstructor
@NoArgsConstructor
data class ErrorMessage(
    private val statusCode: Int,
    private val error: HttpStatus,
    private val message: List<String>
)
