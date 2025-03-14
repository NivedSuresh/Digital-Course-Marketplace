package org.dcmp.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDate



/**
 * Will act as a many-to-many table for user <-> bought course
 * */
@Entity
@Table(
    name = "purchases",
    uniqueConstraints = [UniqueConstraint(columnNames = ["customer_id", "course_id"])]
)
data class Purchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /* This property will help us to load customer_id without loading the customer */
    @Column(name = "customer_id", nullable = false)
    val customerId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    val customer: User? = null,

    /* This property will help us to load course_id without loading the course */
    @Column(name = "course_id", nullable = false)
    val courseId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
    val course: Course? = null,

    @Column(nullable = false)
    val amountPaid: Double,

    @Column(nullable = false)
    val purchaseDate: LocalDate,
)

