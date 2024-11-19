package com.example.sales_crud.model

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GeneratedColumn
import java.time.Instant
import java.util.UUID


@Entity
@Table(name = "user", schema = "public")
@Data
@NoArgsConstructor
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    @Column(
        name = "username",
        length = 50,
        unique = true,
        nullable = false
    )
    val name: String,
    @Column(
        name = "password",
        length = 255,
        nullable = false
    )
    val password: String,

    @Column(
        nullable = false,
        name = "status",
    )
    val status:Boolean = true,

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "role_id",
        nullable = false
    )
    val role: Role,

    @CreationTimestamp
    @Column(
        updatable = false
    )
    val created_at: Instant = Instant.now(),

    @CreationTimestamp
    val updated_at: Instant = Instant.now()

);