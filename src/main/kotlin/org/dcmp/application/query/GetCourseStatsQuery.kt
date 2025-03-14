package org.dcmp.application.query

import org.dcmp.application.dto.StatsDto
import org.dcmp.domain.contracts.Request
import org.springframework.data.domain.Page
import java.time.LocalDate

class GetCourseStatsQuery(val startDate: LocalDate = LocalDate.now().minusYears(1),
                          val endDate: LocalDate = LocalDate.now(),
                          var limit: Int = 10,
                          var page: Int = 1): Request<Page<StatsDto>>
