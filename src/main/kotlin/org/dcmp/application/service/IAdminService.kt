package org.dcmp.application.service

import org.dcmp.application.dto.StatsDto
import org.dcmp.application.query.GetAllUsersQuery
import org.dcmp.application.query.GetCourseStatsQuery
import org.dcmp.domain.entity.User
import org.springframework.data.domain.Page

interface IAdminService {
    fun getUsers(getAllUsersQuery: GetAllUsersQuery): Page<User>
    fun getCourseStats(courseStatsQuery: GetCourseStatsQuery): Page<StatsDto>
}
