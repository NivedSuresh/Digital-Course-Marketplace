package org.dcmp.application.query_handler

import org.dcmp.application.dto.StatsDto
import org.dcmp.application.query.GetCourseStatsQuery
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.infrastructure.persistence.jpa.PurchaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Component
import java.util.function.LongSupplier


@Component
class GetCourseStatsHandler(private val purchaseRepository: PurchaseRepository): RequestHandler<GetCourseStatsQuery, Page<StatsDto>> {

    override fun handle(request: GetCourseStatsQuery): Page<StatsDto> {
        request.offset = maxOf(request.offset, 0)
        request.limit = request.limit.coerceIn(10, 100)
        val pageable: Pageable = PageRequest.of(request.offset, request.limit)

        val stats = purchaseRepository.findPurchaseStatsWithinDateRange(request.startDate, request.endDate, pageable)

        return stats;
    }
}
