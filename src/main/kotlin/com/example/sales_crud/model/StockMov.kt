package com.example.sales_crud.model

import com.example.sales_crud.utils.StockMovEnum
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "stock_mov", schema = "public")
data class StockMov(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ,
    @Column(
        nullable = false,
        name = "quantity"
    )
    var quantity: Int,
    @Column(
        nullable = false,
        name = "description",
        length = 255
    )
    var description: String,
    @Column(
        name = "type",
        nullable = false,
    )
    @Enumerated(EnumType.ORDINAL)
    var stock_type: StockMovEnum,
    @OneToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    var detail_product: Product_Detail,
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
