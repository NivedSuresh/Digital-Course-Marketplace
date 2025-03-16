package org.dcmp.application.command_handler

import org.dcmp.application.command.CreateCourseCommand
import org.dcmp.application.mapper.CourseMapper
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.domain.entity.Course
import org.dcmp.domain.entity.Role
import org.dcmp.domain.exception.EntityNotFoundException
import org.dcmp.domain.exception.ErrorCode
import org.dcmp.domain.exception.UnexpectedStateException
import org.dcmp.infrastructure.persistence.jpa.CourseRepository
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CreateCourseHandler(private val courseRepository: CourseRepository,
                          private val userRepository: UserRepository): RequestHandler<CreateCourseCommand, Course> {

    override fun handle(request: CreateCourseCommand): Course {
        val authentication = SecurityContextHolder.getContext().authentication
        val principalId = authentication.name!!.toLong()

        val isAdmin = authentication.authorities.any { it.authority == "ADMIN" }

        //IF USER IS NOT ADMIN THEN OVERWRITE CREATOR ID WITH PRINCIPAL ID
        if (!isAdmin) {
            request.creatorId = principalId
        }

        if (isAdmin && request.creatorId == null) {
            throw UnexpectedStateException("Creator should be provided", ErrorCode.EXPECTATION_FAILED, HttpStatus.BAD_REQUEST)
        }

        if (!userRepository.existsByIdAndAuthority(request.creatorId!!, Role.CREATOR)) {
            throw EntityNotFoundException("Creator");
        }

        return courseRepository.save(CourseMapper.toEntity(request))
    }

}
