package com.example.sales_crud.services

import com.example.sales_crud.repositories.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleService(
    @Autowired
    private val roleRepository: RoleRepository
) {

}