package org.dcmp.application.dto

import java.math.BigDecimal

interface PurchaseStatsDto {
    fun getTotalAmountPaidEntirely(): BigDecimal
    fun getTotalCoursesSoldEntirely(): Long
}

