package com.example.sales_crud.services

import com.example.sales_crud.model.Permission
import com.example.sales_crud.repositories.PermissionRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PermissionService(
    @Autowired
    private val permissionRepository: PermissionRepository
) {
    @Transactional
    fun createPermissions(names:List<String>): List<Permission>{
        //crea el permiso en una funcion iterativa de una lista "List.map"
        val createPermission = names.map { name ->
            Permission(nombre = name)
        }
        return this.permissionRepository.saveAll(createPermission);
    }

}