package org.dcmp.application.service

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.domain.entity.Course

interface ICourseService {
    fun create(command: CreateCourseCommand): Course
}
