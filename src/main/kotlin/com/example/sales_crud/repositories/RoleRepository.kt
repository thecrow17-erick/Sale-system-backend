package com.example.sales_crud.repositories

import com.example.sales_crud.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RoleRepository: JpaRepository<Role,Long> {
    fun findRoleByName(name: String): Optional<Role>
    fun findRoleById(id: Long): Optional<Role>
}