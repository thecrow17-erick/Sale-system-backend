package com.example.sales_crud.security

import com.example.sales_crud.model.User
import lombok.AllArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.security.SecurityPermission
import java.util.UUID

@AllArgsConstructor
class SecurityUser(
    private val user: User
): UserDetails {

    @Override
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.role.permissions.map { p-> SimpleGrantedAuthority(p.name)}.toMutableList();
    }


    @Override
    override fun getUsername(): String = user.name;

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