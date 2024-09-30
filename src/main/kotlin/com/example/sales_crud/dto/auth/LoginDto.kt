package com.example.sales_crud.dto.auth

import org.jetbrains.annotations.NotNull

data class LoginDto(
    @NotNull
    val username: String,
    @NotNull
    val password: String
);
