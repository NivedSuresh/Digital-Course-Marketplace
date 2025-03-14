package org.dcmp.application.service.impl

import org.dcmp.application.dto.StatsDto
import org.dcmp.application.query.GetAllUsersQuery
import org.dcmp.application.query.GetCourseStatsQuery
import org.dcmp.application.query_handler.GetAllUsersHandler
import org.dcmp.application.query_handler.GetCourseStatsHandler
import org.dcmp.application.service.IAdminService
import org.dcmp.domain.entity.User
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class AdminService(private val getAllUsersHandler: GetAllUsersHandler, private val getCourseStatsHandler: GetCourseStatsHandler): IAdminService {

    override fun getUsers(getAllUsersQuery: GetAllUsersQuery): Page<User> {
        return getAllUsersHandler.handle(getAllUsersQuery);
    }

    override fun getCourseStats(courseStatsQuery: GetCourseStatsQuery): Page<StatsDto> {
        return getCourseStatsHandler.handle(courseStatsQuery);
    }

}
