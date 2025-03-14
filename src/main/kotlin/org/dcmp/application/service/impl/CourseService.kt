package org.dcmp.application.service.impl

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.command_handler.CreateCourseHandler
import org.dcmp.application.dto.CourseDto
import org.dcmp.application.query.GetAllCoursesQuery
import org.dcmp.application.query_handler.GetAllCoursesHandler
import org.dcmp.application.service.ICourseService
import org.dcmp.domain.contracts.PagedResult
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class CourseService(private val createCourseHandler: CreateCourseHandler,
                    private val getAllCoursesHandler: GetAllCoursesHandler): ICourseService {

    override fun create(command: CreateCourseCommand): Course {
        return this.createCourseHandler.handle(command);
    }

    override fun findByCreatorPaginated(command: GetAllCoursesQuery): Page<Course> {
        return getAllCoursesHandler.handle(command)
    }

}
