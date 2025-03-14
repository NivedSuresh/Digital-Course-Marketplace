package org.dcmp.application.service.impl

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.command_handler.CreateCourseHandler
import org.dcmp.application.service.ICourseService
import org.dcmp.domain.entity.Course
import org.springframework.stereotype.Service

@Service
class CourseService(private val createCourseHandler: CreateCourseHandler): ICourseService {

    override fun create(command: CreateCourseCommand): Course {
        return this.createCourseHandler.handle(command);
    }

}
