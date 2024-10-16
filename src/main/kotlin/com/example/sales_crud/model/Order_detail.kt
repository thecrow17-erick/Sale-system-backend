package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "order_detail", schema = "public")
data class Order_detail(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
    @Column(
        nullable = false,
        name = "import"
    )
    private val import: Double,
    @Column(
        nullable = false,
        name = "stock"
    )
    val stock: Int,
    @ManyToOne
    @JoinColumn(
        name = "order_id",
        nullable = false,
    )
    val order: Order,

    @ManyToOne
    @JoinColumn(
        name = "product_detail_id",
        nullable = false
    )
    val productDetail: Product_Detail,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    private val created_at: Instant = Instant.now(),
)
