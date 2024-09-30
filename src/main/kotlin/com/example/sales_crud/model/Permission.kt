package com.example.sales_crud.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "permissions", schema = "public")
@Data
@NoArgsConstructor
data class Permission(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(
        nullable = false,
        unique = true,
        name = "name",
        length = 60
    )
    val name: String
);
