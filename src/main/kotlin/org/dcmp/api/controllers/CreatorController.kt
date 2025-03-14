package org.dcmp.api.controllers

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.dto.CourseDto
import org.dcmp.application.mapper.CourseMapper
import org.dcmp.application.service.impl.CourseService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/creator")
class CreatorController(private val courseService: CourseService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody command: CreateCourseCommand): CourseDto {
        logger.info("Creating course")
        val course = this.courseService.create(command)
        return CourseMapper.toDto(course)
    }

}
