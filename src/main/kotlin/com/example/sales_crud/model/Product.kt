package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "product", schema = "public")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
    @Column(
        name = "name",
        nullable = false,
        length = 50
    )
    private val name: String,
    @Column(
        name = "status",
        nullable = false
    )
    private val status:Boolean = true,
    @Column(
        name = "description",
        nullable = false,
        length = 255
    )
    private val description: String,
    @Column(
        name = "price",
        nullable = false,
    )
    private val price: Double,
    @Column(
        name = "code",
        nullable = false,
        length = 60
    )
    private val code: String,
    @Column(
        name = "image",
        nullable = false,
        length = 255
    )
    private val img_url: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_id",
        nullable = false,
    )
    private val category: Category,
    @OneToMany(
        fetch = FetchType.LAZY,
    )
    private val details: MutableList<Product_Detail> = mutableListOf(),
    @CreationTimestamp
    @Column(
        updatable = false
    )
    private val created_at: Instant = Instant.now(),
    @CreationTimestamp
    private val updated_at: Instant = Instant.now()
)
