package com.example.sales_crud.security

import com.example.sales_crud.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.security.SecurityPermission

abstract class SecurityUser(
    private final val user: User
): UserDetails {

    @Override
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.role.permissions.map { p-> SimpleGrantedAuthority(p.nombre)}.toMutableList();
    }

    @Override
    override fun getUsername(): String = user.code;

    @Override
    override fun isEnabled(): Boolean = true;

    @Override
    override fun isCredentialsNonExpired(): Boolean = true;

    @Override
    override fun getPassword(): String = user.password;

    @Override
    override fun isAccountNonExpired(): Boolean = true;

    @Override
    override fun isAccountNonLocked(): Boolean = true;
}