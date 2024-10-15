package com.example.sales_crud.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

data class Product_Detail(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
    @Column(
        name = "stock",
        nullable = false
    )
    private val stock: Int? = 0,
    @Column(
        name = "size",
        nullable = false
    )
    private val size: String? = "No tiene",
    @Column(
        name = "color",
        nullable = false
    )
    private val color: String? = "No tiene",
    @CreationTimestamp
    @Column(
        updatable = false
    )
    private val created_at: Instant = Instant.now(),
    @CreationTimestamp
    private val updated_at: Instant = Instant.now()
)
