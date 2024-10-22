package com.example.sales_crud.dto.auth

import org.jetbrains.annotations.NotNull

data class LoginDto(
    @field:NotNull
    val username: String,
    @field:NotNull
    val password: String
);
