package org.dcmp.application.dto

import org.dcmp.domain.contracts.PagedResult


data class StatsDto(
    val courseStats: PagedResult<CourseStatsDto>,
    val purchaseStats: PurchaseStatsDto
)

