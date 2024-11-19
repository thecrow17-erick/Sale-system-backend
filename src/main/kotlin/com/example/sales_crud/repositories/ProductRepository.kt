package com.example.sales_crud.repositories

import com.example.sales_crud.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    fun findProductByCodeOrName(code: String, name: String): List<Product>
    fun findAllBy(pageable: Pageable): Page<Product>;
    @Query("SELECT p FROM Product p WHERE p.name ILIKE %:search% OR p.code ILIKE %:search%")
    fun searchByNameOrCode(
        @Param("search") search: String,
        pageable: Pageable
    ): Page<Product>;
    fun findProductById(id: Long): Optional<Product>;
    @Query("SELECT p FROM Product p WHERE (p.code = :code OR p.name = :name) AND p.id <> :id")
    fun findProductByCodeOrNameAndIdNot(@Param("code")code: String, @Param("name") name: String, @Param("id") id:Long): List<Product>;
    fun findProductByCode(code: String): Optional<Product>;
}