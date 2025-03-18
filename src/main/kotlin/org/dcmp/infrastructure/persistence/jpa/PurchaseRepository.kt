package org.dcmp.infrastructure.persistence.jpa

import org.dcmp.application.dto.StatsDto
import org.dcmp.domain.entity.Course
import org.dcmp.domain.entity.Purchase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface PurchaseRepository : JpaRepository<Purchase, Long>, PagingAndSortingRepository<Purchase, Long> {


    fun existsByCustomerIdAndCourseId(customerId: Long, courseId: Long): Boolean


    @Query(
        value = """
    SELECT p.course.id as courseId, c.title as title, SUM(p.amountPaid) as totalAmountPaid
    FROM Purchase p
    JOIN p.course c
    WHERE (COALESCE(:startDate, '1970-01-01') IS NULL OR p.purchaseDate >= :startDate)
      AND (COALESCE(:endDate, '2099-12-31') IS NULL OR p.purchaseDate <= :endDate)
    GROUP BY p.course.id, c.title
    ORDER BY SUM(p.amountPaid) DESC
    """,
        countQuery = """
    SELECT COUNT(DISTINCT p.course.id)
    FROM Purchase p
    WHERE (COALESCE(:startDate, '1970-01-01') IS NULL OR p.purchaseDate >= :startDate)
      AND (COALESCE(:endDate, '2099-12-31') IS NULL OR p.purchaseDate <= :endDate)
    """
    )
    fun findPurchaseStatsWithinDateRange(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        pageable: Pageable
    ): Page<StatsDto>


    @Query("SELECT p.course FROM Purchase p WHERE p.customerId = :customerId")
    fun findAllPurchasedCourseIdByCustomerId(customerId: Long, pageable: Pageable): Page<Course>
}

