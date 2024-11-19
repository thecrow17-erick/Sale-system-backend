package com.example.sales_crud.repositories

import com.example.sales_crud.model.Product_Detail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductDetailRepository: JpaRepository<Product_Detail, Long> {
    fun findAllBy(pageable: Pageable): Page<Product_Detail>;
    @Query("SELECT p FROM Product_Detail p WHERE p.product.name ILIKE %:search% OR p.product.code ILIKE %:search%")
    fun searchByNameOrCode(
        @Param("search") search: String,
        pageable: Pageable
    ): Page<Product_Detail>
}