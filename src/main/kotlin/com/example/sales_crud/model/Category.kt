package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "category",schema = "public")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(
        name = "name",
        length = 50,
        nullable = false
    )
    var name: String,
    @Column(
        name = "description",
        length = 255,
        nullable = false
    )
    var description: String,
    @Column(
        name = "status",
        nullable = false
    )
    var status: Boolean = true,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),
    @UpdateTimestamp
    var updated_at: Instant = Instant.now()
)
