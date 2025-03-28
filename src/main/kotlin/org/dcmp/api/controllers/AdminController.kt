package org.dcmp.api.controllers

import org.dcmp.application.dto.CourseStatsDto
import org.dcmp.application.dto.StatsDto
import org.dcmp.application.dto.UserDTO
import org.dcmp.application.mapper.UserMapper
import org.dcmp.application.query.GetAllUsersQuery
import org.dcmp.application.query.GetCourseStatsQuery
import org.dcmp.application.service.IAdminService
import org.dcmp.domain.contracts.PagedResult
import org.dcmp.domain.entity.Role
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController


@RestController
class AdminController(private val adminService: IAdminService) {

    @GetMapping("/user")
    fun getAllUsers(@ModelAttribute getAllUsersQuery: GetAllUsersQuery): PagedResult<UserDTO> {
        if (getAllUsersQuery.roles == null || getAllUsersQuery.roles!!.isEmpty()) {
            getAllUsersQuery.roles = mutableListOf(Role.CREATOR, Role.CUSTOMER)
        }

        val users = adminService.getUsers(getAllUsersQuery)
        return PagedResult(
            items = users.content.map { user -> UserMapper.toDto(user) },
            totalRecords = users.totalElements,
            limit = users.size.toLong(),
            offset = (users.number * users.size)
        )
    }


    @GetMapping("/admin/stats")
    fun getStats(@ModelAttribute courseStatsQuery: GetCourseStatsQuery): StatsDto {
        courseStatsQuery.principalId = null;
        return adminService.getCourseStats(courseStatsQuery)
    }


}
