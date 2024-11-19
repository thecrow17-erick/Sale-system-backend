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
    var import: Double,
    @Column(
        nullable = false,
        name = "quantity"
    )
    var quantity: Int,
    @ManyToOne
    @JoinColumn(
        name = "order_id",
        nullable = false,
    )
    var order: Order,
    @ManyToOne
    @JoinColumn(
        name = "product_detail_id",
        nullable = false
    )
    var productDetail: Product_Detail,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),
)
