package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "orders", schema = "public")
data class Order(
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(
        name = "client",
        nullable = false,
        length = 50
    )
    val client: String,
    @Column(
        name = "total",
        nullable = false
    )
    var total: Double,
    @OneToMany(
        mappedBy = "order",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    val orderDetail: List<Order_detail> = listOf(),
    @ManyToOne(
        fetch = FetchType.LAZY,
    )
    val user: User,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),
    @UpdateTimestamp
    val updated_at: Instant = Instant.now()
)
