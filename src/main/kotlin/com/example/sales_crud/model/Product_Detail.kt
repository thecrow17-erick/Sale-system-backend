package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "detail_product", schema = "public")
data class Product_Detail(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(
        name = "stock",
        nullable = false
    )
    var stock: Int = 0,
    @Column(
        name = "discount",
        nullable = false
    )
    var discount: Double = 0.0,
    @Column(
        name = "size",
        nullable = false,
        length = 60
    )
    var size: String = "No tiene",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "product_id",
        nullable = false,
    )
    var product: Product,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),
    @UpdateTimestamp
    val updated_at: Instant = Instant.now()
)
