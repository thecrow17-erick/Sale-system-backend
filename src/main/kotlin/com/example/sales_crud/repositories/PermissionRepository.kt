package com.example.sales_crud.repositories

import com.example.sales_crud.model.Permission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PermissionRepository: JpaRepository<Permission,Long> {
    fun findPermissionByName(name: String): Optional<Permission>
}