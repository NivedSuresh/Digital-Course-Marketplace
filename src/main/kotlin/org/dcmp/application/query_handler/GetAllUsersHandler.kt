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

        if (request.page == null){
            request.page = 1
        }
        if (request.limit == null){
            request.limit = 10
        }

        request.page = maxOf(request.page!!, 1)
        request.limit = request.limit!!.coerceIn(1, 100)
        val pageable: Pageable = PageRequest.of(request.page!! - 1, request.limit!!)

        return userRepository.findAllByAuthorityIn(request.roles!!, pageable)
    }
}

