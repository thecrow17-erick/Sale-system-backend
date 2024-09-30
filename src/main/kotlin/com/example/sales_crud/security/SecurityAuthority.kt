package com.example.sales_crud.security

import com.example.sales_crud.model.Permission
import org.springframework.security.core.GrantedAuthority

class SecurityAuthority(
    private val permission: Permission
):GrantedAuthority {

    @Override
    override fun getAuthority(): String = permission.name.toString();
}