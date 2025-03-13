package org.dcmp.application.command_handler

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.domain.contracts.cqrs.RequestHandler
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class LoginHandler(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : RequestHandler<LoginCommand, LoginResponse> {


    @EventListener
    override fun handle(request: LoginCommand): LoginResponse {
        throw Exception("Not implemented yet")
    }

}

