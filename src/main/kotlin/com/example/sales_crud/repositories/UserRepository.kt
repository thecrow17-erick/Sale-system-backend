package com.example.sales_crud.repositories

import com.example.sales_crud.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, Long>{
    fun findUserByName(name: String): Optional<User>;
    fun findAllByNameNotIn(name: List<String>, pageable: Pageable): Page<User>;
    @Query("SELECT U FROM User U WHERE U.name ILIKE %:name%  AND U.name NOT IN :not")
    fun searchFindUser(
        @Param("not") not: List<String>,
        @Param("name") name: String,
        pageable: Pageable
    ): Page<User>;
    fun findUserById(id: UUID):Optional<User>;
}