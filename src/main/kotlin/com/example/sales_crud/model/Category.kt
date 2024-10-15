package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "category",schema = "public")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
    @Column(
        name = "name",
        length = 50,
        nullable = false
    )
    private val name: String,
    @Column(
        name = "description",
        length = 255,
        nullable = false
    )
    private val description: String,
    @Column(
        name = "status",
        nullable = false
    )
    private val status: Boolean = true,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    private val created_at: Instant = Instant.now(),
    @CreationTimestamp
    private val updated_at: Instant = Instant.now()
)
