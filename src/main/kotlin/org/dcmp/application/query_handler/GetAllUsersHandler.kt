package org.dcmp.application.query_handler

import org.dcmp.application.query.GetAllUsersQuery
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.domain.entity.User
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetAllUsersHandler(private val userRepository: UserRepository) : RequestHandler<GetAllUsersQuery, Page<User>> {

    override fun handle(request: GetAllUsersQuery): Page<User> {
        request.offset = maxOf(request.offset, 0)
        request.limit = request.limit.coerceIn(10, 100)
        val pageNumber = request.offset / request.limit
        val pageable: Pageable = PageRequest.of(pageNumber, request.limit)
        return userRepository.findAllByAuthorityIn(request.roles!!, pageable)
    }
}

