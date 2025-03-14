package org.dcmp.application.service

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.query.GetAllCoursesQuery
import org.dcmp.domain.entity.Course
import org.springframework.data.domain.Page

interface ICourseService {
    fun create(command: CreateCourseCommand): Course
    fun findByCreatorPaginated(command: GetAllCoursesQuery): Page<Course>
}
