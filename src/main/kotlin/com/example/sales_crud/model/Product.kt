package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@Table(name = "product", schema = "public")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(
        name = "name",
        nullable = false,
        length = 50
    )
    var name: String,
    @Column(
        name = "status",
        nullable = false
    )
    var status:Boolean = true,
    @Column(
        name = "description",
        nullable = false,
        length = 255
    )
    var description: String,
    @Column(
        name = "price",
        nullable = false,
    )
    var price: Double,
    @Column(
        name = "code",
        nullable = false,
        length = 60,
        unique = true
    )
    var code: String,
    @Column(
        name = "image",
        nullable = false,
        length = 255
    )
    var img_url: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_id",
        nullable = false,
    )
    var category: Category,
    @OneToMany(
        cascade = [CascadeType.ALL], fetch = FetchType.LAZY
    )
    val details: MutableList<Product_Detail> = mutableListOf(),
    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),
    @UpdateTimestamp
    var updated_at: Instant = Instant.now()
)
