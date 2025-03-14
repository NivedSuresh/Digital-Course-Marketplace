package org.dcmp.api.controllers

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.dto.CourseDto
import org.dcmp.application.mapper.CourseMapper
import org.dcmp.application.query.GetAllCoursesQuery
import org.dcmp.application.service.ICourseService
import org.dcmp.application.service.impl.CourseService
import org.dcmp.domain.contracts.PagedResult
import org.dcmp.domain.entity.Course
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
class CourseController(private val courseService: ICourseService) {

    private val logger = LoggerFactory.getLogger(javaClass)


    @GetMapping("/course")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCourses(@ModelAttribute command: GetAllCoursesQuery): PagedResult<CourseDto> {
        logger.info("Getting all courses")

        val courses: Page<Course> = this.courseService.findByCreatorPaginated(command)

        return PagedResult(
            items = courses.content.map { course -> CourseMapper.toDto(course) },
            totalRecords = courses.totalElements,
            limit = courses.size.toLong(),
            offset = (courses.number * courses.size)
        )
    }

}
