package com.example.sales_crud.error.dto

import org.springframework.http.HttpStatus


data class ErrorMessage(
    val statusCode: Int,
    val error: HttpStatus,
    val message: List<String>
)
