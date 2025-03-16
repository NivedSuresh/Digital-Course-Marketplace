package org.dcmp.application.mapper

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.dto.CourseDto
import org.dcmp.domain.entity.Course

class CourseMapper {

    companion object {

        fun toEntity(command: CreateCourseCommand) : Course {
            return Course(null, command.title, command.description, command.price, command.creatorId!!);
        }

        fun toDto(course: Course): CourseDto {
            return CourseDto(course.id!!, course.title, course.description, course.price, course.creatorId)
        }
    }

}
