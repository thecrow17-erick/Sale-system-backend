package com.example.sales_crud.model

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant


@Entity
@Table(name = "user", schema = "public")
@Data
@NoArgsConstructor
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(
        name = "name",
        length = 50,
        nullable = false
    )
    val name: String,
    @Column(
        name = "code",
        length = 50,
        nullable = false
    )
    val code: String,
    @Column(
        name= "sexo",
        length = 1,
        nullable = false
    )
    val sex: String,
    @Column(
        name = "password",
        length = 255,
        nullable = false
    )
    val password: String,
    @Column(
        name = "telephone",
        length = 8,
        nullable = false
    )
    val telephone: String,

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