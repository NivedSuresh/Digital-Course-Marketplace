package org.dcmp.application.dto

import org.dcmp.domain.contracts.PagedResult
import java.math.BigDecimal

interface StatsDto {
    fun getCourseId(): Long?
    fun getTitle(): String?
    fun getTotalAmountPaid(): BigDecimal?
}
