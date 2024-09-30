package com.example.sales_crud.repositories

import com.example.sales_crud.model.Permission
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PermissionRepository: JpaRepository<Permission,Long> {
    fun findPermissionByNombre(nombre: String): Optional<Permission>
}