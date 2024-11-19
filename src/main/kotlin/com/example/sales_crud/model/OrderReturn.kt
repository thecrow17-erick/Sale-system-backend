package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant


@Entity
@Table(name = "order_return", schema = "public")
data class OrderReturn(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(
        nullable = false,
        name = "description",
        length = 255
    )
    var description: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    var user: User,
    @OneToOne
    @JoinColumn(
        name = "order_id",
        nullable = false
    )
    var order: Order,
    @Column(
        nullable = false,
        name = "status"
    )
    var status: Boolean = false,
    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),
    @UpdateTimestamp
    @Column(
        updatable = true
    )
    val updated_at: Instant = Instant.now()
)
