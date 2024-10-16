package com.example.sales_crud.repositories

import com.example.sales_crud.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findUserByName(name: String): Optional<User>
}