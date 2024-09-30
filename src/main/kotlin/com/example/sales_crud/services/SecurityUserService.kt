package com.example.sales_crud.services

import com.example.sales_crud.error.exception.NotFoundException
import com.example.sales_crud.repositories.UserRepository
import com.example.sales_crud.security.SecurityUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityUserService(
    private val userRepository: UserRepository,
):UserDetailsService {

    @Override
    override fun loadUserByUsername(username: String): UserDetails {
        val optUser = this.userRepository.findUserByName(username);
        if (optUser.isPresent) {
            return SecurityUser(optUser.get());
        }
        throw NotFoundException(
                "Usuario no encontrado"
        );
    }
}