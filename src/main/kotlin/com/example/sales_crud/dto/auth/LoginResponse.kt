package com.example.sales_crud.dto.auth

import com.example.sales_crud.model.User

data class LoginResponse(
    val user:User,
    val token:String
)
