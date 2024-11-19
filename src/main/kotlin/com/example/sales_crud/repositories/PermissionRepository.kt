package com.example.sales_crud.repositories

import com.example.sales_crud.model.Permission
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PermissionRepository: JpaRepository<Permission,Long> {
    fun findPermissionByName(name: String): Optional<Permission>
    fun searchByNameLikeIgnoreCase(name: String, pageable: Pageable): Page<Permission>;
    fun findAllBy(pageable: Pageable): Page<Permission>
}