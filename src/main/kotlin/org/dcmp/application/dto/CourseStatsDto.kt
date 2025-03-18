package org.dcmp.application.dto

import java.math.BigDecimal

interface CourseStatsDto {
    fun getCourseId(): Long?
    fun getTitle(): String?
    fun getTotalAmountPaid(): BigDecimal?
    fun getTotalQuantitySold(): Long?
}

