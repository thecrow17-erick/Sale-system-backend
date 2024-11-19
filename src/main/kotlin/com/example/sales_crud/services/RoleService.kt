package com.example.sales_crud.services

import com.example.sales_crud.dto.user.CreateRoleDto
import com.example.sales_crud.error.exception.BadRequestException
import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.model.Permission
import com.example.sales_crud.model.Role
import com.example.sales_crud.repositories.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RoleService(
    @Autowired
    private val roleRepository: RoleRepository
) {
    fun createRole(createRoleDto: CreateRoleDto,permissions: MutableList<Permission>):Role{
        val findRole = this.roleRepository.findRoleByName(createRoleDto.name);
        if(findRole.isPresent)
            throw BadRequestException("Role is present")

        val createRole = Role(
            name = createRoleDto.name,
            description = createRoleDto.description,
            permissions = permissions
        );
        return this.roleRepository.save(createRole);
    }

    fun addPermissionRol(role:Role, permissions: MutableList<Permission>):Role{
        role.permissions.addAll(permissions);
        return this.roleRepository.save(role);
    }

    fun findRole(name:String): Optional<Role> = this.roleRepository.findRoleByName(name);

    fun findIdRol(id: Long): Role {
        val findRole = this.roleRepository.findRoleById(id);
        if (findRole.isEmpty)
            throw NotFoundException("Rol no encontrado")
        return findRole.get();
    }
}