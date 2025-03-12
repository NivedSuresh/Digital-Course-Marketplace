package org.dcmp.infrastructure.persistence.jpa

import org.dcmp.domain.entity.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface PurchaseRepository : JpaRepository<Purchase, Long> {
    fun findByCustomerId(customerId: Long): List<Purchase>
    fun findByPurchaseDateBetween(startDate: LocalDate, endDate: LocalDate): List<Purchase>
}

