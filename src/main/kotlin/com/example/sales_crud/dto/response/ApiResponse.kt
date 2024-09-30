package com.example.sales_crud.dto.response

data class ApiResponse<T>(
    val statusCode: Int,
    val message: String,
    val data: T
);