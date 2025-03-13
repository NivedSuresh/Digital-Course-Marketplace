package org.dcmp.application.query_handler

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.query.LoginQuery
import org.dcmp.domain.contracts.cqrs.RequestHandler
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class LoginHandler(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : RequestHandler<LoginQuery, LoginResponse> {


    @EventListener
    override fun handle(request: LoginQuery): LoginResponse {
        throw Exception("Not implemented yet")
    }

}

