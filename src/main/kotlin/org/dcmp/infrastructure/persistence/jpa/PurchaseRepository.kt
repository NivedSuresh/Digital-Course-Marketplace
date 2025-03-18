package org.dcmp.infrastructure.persistence.jpa

import org.dcmp.application.dto.CourseStatsDto
import org.dcmp.application.dto.PurchaseStatsDto
import org.dcmp.domain.entity.Course
import org.dcmp.domain.entity.Purchase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.math.BigDecimal
import java.time.LocalDate

interface PurchaseRepository : JpaRepository<Purchase, Long>, PagingAndSortingRepository<Purchase, Long> {


    fun existsByCustomerIdAndCourseId(customerId: Long, courseId: Long): Boolean


    @Query(
        value = """
    SELECT p.course.id as courseId, 
           c.title as title, 
           SUM(p.amountPaid) as totalAmountPaid, 
           COUNT(p.id) as totalQuantitySold
    FROM Purchase p
    JOIN p.course c
    WHERE (:principalId IS NULL OR p.course.creatorId = :principalId)
      AND (COALESCE(:startDate, '1970-01-01') IS NULL OR p.purchaseDate >= :startDate)
      AND (COALESCE(:endDate, '2099-12-31') IS NULL OR p.purchaseDate <= :endDate)
    GROUP BY p.course.id, c.title
    ORDER BY SUM(p.amountPaid) DESC
    """,
        countQuery = """
    SELECT COUNT(DISTINCT p.course.id)
    FROM Purchase p
    WHERE (:principalId IS NULL OR p.course.creatorId = :principalId)
      AND (COALESCE(:startDate, '1970-01-01') IS NULL OR p.purchaseDate >= :startDate)
      AND (COALESCE(:endDate, '2099-12-31') IS NULL OR p.purchaseDate <= :endDate)
    """
    )
    fun findPurchaseStatsWithinDateRange(
        @Param("startDate") startDate: LocalDate?,
        @Param("endDate") endDate: LocalDate?,
        @Param("principalId") principalId: Long?,
        pageable: Pageable
    ): Page<CourseStatsDto>


    @Query("SELECT p.course FROM Purchase p WHERE p.customerId = :customerId")
    fun findAllPurchasedCourseIdByCustomerId(customerId: Long, pageable: Pageable): Page<Course>

    @Query("""
        SELECT COALESCE(SUM(p.amountPaid), 0) as totalAmountPaidEntirely, COUNT(p) as totalCoursesSoldEntirely
        FROM Purchase p 
        WHERE (:principalId IS NULL OR p.course.creatorId = :principalId)
    """)
    fun findPurchaseSumAndCountByCustomerId(@Param("principalId") principalId: Long?): PurchaseStatsDto

}

