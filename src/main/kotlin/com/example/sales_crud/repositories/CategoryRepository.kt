package com.example.sales_crud.repositories

import com.example.sales_crud.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
    fun findByName(name: String): Optional<Category>;
    fun findAllBy(pageable: Pageable): Page<Category>;
    @Query("SELECT c FROM Category c WHERE c.name ILIKE %:searchTerm% OR c.description ILIKE %:searchTerm% ")
    fun searchByNameOrDescription(
        @Param("searchTerm") searchTerm: String,
        pageable: Pageable
    ): Page<Category>
}