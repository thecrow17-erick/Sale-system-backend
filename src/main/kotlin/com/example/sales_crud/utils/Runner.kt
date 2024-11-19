package com.example.sales_crud.utils

import com.example.sales_crud.dto.user.CreateRoleDto
import com.example.sales_crud.dto.user.CreateUserDto
import com.example.sales_crud.model.Role
import com.example.sales_crud.services.PermissionService
import com.example.sales_crud.services.RoleService
import com.example.sales_crud.services.UserService
import lombok.RequiredArgsConstructor
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class Runner(
    private val userService: UserService,
    private val permissionService: PermissionService,
    private val roleService: RoleService
) : CommandLineRunner {

    @Override
    override fun run(vararg args: String) {
        //creo los permisos
        val permissions = mutableListOf(
            "ver usuario",
            "crear usuario",
            "editar rol de usuario",
            "reestablecer password",
            "ver rol",
            "crear rol",
            "editar rol",
            "eliminar rol",
            "ver categoria",
            "editar categoria",
            "crear categoria",
            "eliminar categoria",
            "ver producto",
            "crear producto",
            "editar producto",
            "eliminar producto",
            "ver detalle del producto",
            "crear detalle del producto",
            "editar detalle del producto",
            "ver entrada de stock",
            "crear entrada de stock",
            "eliminar entrada de stock",
            "ver salida de stock",
            "crear salida de stock",
            "eliminar salida de stock",
            "ver orden de venta",
            "crear orden de venta",
            "eliminar orden de venta",
            "ver devolucion de venta",
            "crear devolucion de venta",
            "eliminar devolucion de venta",
        );
        val createPermission = this.permissionService.createPermissions(permissions);
        //pregunto si hay permisos nuevos para crear
        if(createPermission.size > 0){
            //pregunto si el admin existe
            val findUser = this.userService.findUser("admin");
            if(findUser.isEmpty) {
                //creo el rol admin
                val createRol = this.roleService.createRole(
                    createRoleDto = CreateRoleDto(
                        name = "Admin",
                        description = "Administrador de todo el sistema"
                    ),
                    permissions = createPermission
                );
                //ahora si creo mi usuario
                val createUser = this.userService.createUser(
                    CreateUserDto(
                        username = "admin",
                        password = "123456",
                        role = createRol.id
                    )
                );
            }else{
                //ahora si existe el usuario actualizo los permisos de los roles
                val updateRolePermission = this.roleService.addPermissionRol(findUser.get().role, createPermission);
            }

        }
    }
}