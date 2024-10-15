package com.example.sales_crud.error.dto

import org.springframework.http.HttpStatus


data class ErrorMessage(
    private val statusCode: Int,
    private val error: HttpStatus,
    private val message: List<String>
)
