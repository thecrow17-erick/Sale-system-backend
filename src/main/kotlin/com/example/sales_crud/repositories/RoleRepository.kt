package com.example.sales_crud.repositories

import com.example.sales_crud.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role,Long> {
    fun findRoleByName(name: String): Optional<Role>
}