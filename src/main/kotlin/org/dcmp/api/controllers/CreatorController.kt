package org.dcmp.api.controllers

import jakarta.validation.Valid
import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.dto.CourseDto
import org.dcmp.application.dto.StatsDto
import org.dcmp.application.mapper.CourseMapper
import org.dcmp.application.query.GetAllCoursesQuery
import org.dcmp.application.query.GetCourseStatsQuery
import org.dcmp.application.service.IAdminService
import org.dcmp.application.service.ICourseService
import org.dcmp.application.service.impl.CourseService
import org.dcmp.domain.contracts.PagedResult
import org.dcmp.domain.entity.Course
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/creator")
class CreatorController(private val courseService: ICourseService, private val adminService: IAdminService) {

    private val logger = LoggerFactory.getLogger(javaClass)


    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody @Valid command: CreateCourseCommand): CourseDto {
        logger.info("Creating course")
        val course = this.courseService.create(command)
        return CourseMapper.toDto(course)
    }


    @GetMapping("/course")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCourses(@ModelAttribute command: GetAllCoursesQuery): PagedResult<CourseDto> {

        logger.info("Getting all courses")

        /*
         * Ensuring the creator can only access their own courses
         * by explicitly setting the authenticated user's ID as the creator ID.
         */
        command.creatorId = SecurityContextHolder.getContext().authentication.name.toLong()

        val courses: Page<Course> = this.courseService.findByCreatorPaginated(command)

        return PagedResult(
            items = courses.content.map { course -> CourseMapper.toDto(course) },
            totalRecords = courses.totalElements,
            limit = courses.size.toLong(),
            offset = (courses.number * courses.size)
        )
    }


    @GetMapping("/stats")
    fun getStats(@ModelAttribute courseStatsQuery: GetCourseStatsQuery, @AuthenticationPrincipal principal: UserDetails): StatsDto {
        courseStatsQuery.principalId = principal.username.toLong();
        return adminService.getCourseStats(courseStatsQuery)
    }

}
