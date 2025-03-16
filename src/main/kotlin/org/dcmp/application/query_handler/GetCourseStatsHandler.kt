package org.dcmp.application.query_handler

import org.dcmp.application.dto.StatsDto
import org.dcmp.application.query.GetCourseStatsQuery
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.infrastructure.persistence.jpa.PurchaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component


@Component
class GetCourseStatsHandler(private val purchaseRepository: PurchaseRepository): RequestHandler<GetCourseStatsQuery, Page<StatsDto>> {

    override fun handle(request: GetCourseStatsQuery): Page<StatsDto> {

        if (request.page == null){
            request.page = 1
        }
        if (request.limit == null){
            request.limit = 10
        }

        request.page = maxOf(request.page!!, 1)
        request.limit = request.limit!!.coerceIn(1, 100)
        val pageable: Pageable = PageRequest.of(request.page!! - 1, request.limit!!)

        val stats = purchaseRepository.findPurchaseStatsWithinDateRange(request.startDate, request.endDate, pageable)

        return stats;
    }
}
