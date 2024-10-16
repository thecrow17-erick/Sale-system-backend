package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "orders", schema = "public")
data class Order(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private val id: Long = 0,
    @Column(
        name = "client",
        nullable = false,
        length = 50
    )
    private val client: String,
    @Column(
        name = "total",
        nullable = false
    )
    private val total: Double,
    @OneToMany(
        mappedBy = "order",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    private val orderDetail: List<Order_detail> = listOf(),
    @ManyToOne(
        fetch = FetchType.LAZY,
    )
    private val user: User,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    private val created_at: Instant = Instant.now(),
    @CreationTimestamp
    private val updated_at: Instant = Instant.now()
)
