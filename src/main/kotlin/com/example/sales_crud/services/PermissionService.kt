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
    fun createPermissions(names: List<String>): MutableList<Permission> {
        // Itera sobre los nombres y crea los permisos si no existen
        val createPermission:MutableList<Permission> = names.mapNotNull { name ->
            val findPermission = permissionRepository.findPermissionByName(name)
            if (findPermission.isEmpty) {
                Permission(name = name)
            }else{
                null
            }
        }.toMutableList()
        // Si la lista tiene permisos nuevos, los guarda, si no, retorna lista vacía
        return if (createPermission.isNotEmpty()) {
            permissionRepository.saveAll(createPermission)
        } else {
            mutableListOf();
        }
    }

}