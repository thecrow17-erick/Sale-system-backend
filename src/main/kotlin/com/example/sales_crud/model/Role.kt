package com.example.sales_crud.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(name = "role", schema = "public")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(
        name = "name",
        length = 50,
        nullable = false,
        unique = true
    )
    val name: String,
    @Column(
        name = "description",
        length = 255,
        nullable = false
    )
    val description: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permiso",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permiso_id")]
    )
    val permissions: MutableList<Permission> = mutableListOf(),

    @Column(
        nullable = false,
        name = "status",
    )
    val status:Boolean = true,

    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),

    @CreationTimestamp
    val updated_at: Instant = Instant.now()
);
