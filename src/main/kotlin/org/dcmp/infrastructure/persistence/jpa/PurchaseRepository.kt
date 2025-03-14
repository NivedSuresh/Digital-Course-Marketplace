package org.dcmp.infrastructure.persistence.jpa

import org.dcmp.application.dto.StatsDto
import org.dcmp.domain.entity.Purchase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface PurchaseRepository : JpaRepository<Purchase, Long> {


    fun existsByCustomerIdAndCourseId(customerId: Long, courseId: Long): Boolean


    @Query(
        value = """
        SELECT p.course_id, c.title, SUM(p.amount_paid) AS total_amount_paid
        FROM purchases p
        JOIN courses c ON c.id = p.course_id
        WHERE (COALESCE(:startDate, NULL)::DATE IS NULL OR p.purchase_date >= :startDate)
          AND (COALESCE(:endDate, NULL)::DATE IS NULL OR p.purchase_date <= :endDate)
        GROUP BY p.course_id, c.title
        ORDER BY total_amount_paid DESC
    """,
        countQuery = """
    SELECT COUNT(DISTINCT p.course_id)
    FROM purchases p
    WHERE (:startDate IS NULL OR p.purchase_date >= :startDate)
      AND (:endDate IS NULL OR p.purchase_date <= :endDate)
    """,
        nativeQuery = true
    )
    fun findPurchaseStatsWithinDateRange(startDate: LocalDate, endDate: LocalDate, pageable: Pageable): Page<StatsDto>


}

