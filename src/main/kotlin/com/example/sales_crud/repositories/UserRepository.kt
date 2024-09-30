package com.example.sales_crud.repositories

import com.example.sales_crud.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long>{
    fun findUserByCode(code: String): Optional<User>
}