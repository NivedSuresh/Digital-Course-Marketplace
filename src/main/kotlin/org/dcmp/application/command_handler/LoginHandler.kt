package org.dcmp.application.command_handler

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.domain.contracts.cqrs.RequestHandler
import org.dcmp.domain.exception.ErrorCode
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.dcmp.infrastructure.security.DcmpUserDetails
import org.dcmp.infrastructure.security.DcmpUserDetailsService
import org.dcmp.presentation.advice.UnexpectedStateException
import org.springframework.context.event.EventListener
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class LoginHandler(
    private val authenticationManager: AuthenticationManager
) : RequestHandler<LoginCommand, LoginResponse> {


    @EventListener
    override fun handle(request: LoginCommand): LoginResponse {
        val token = UsernamePasswordAuthenticationToken(request.email, request.password)
        val authentication = authenticationManager.authenticate(token)

        val dcmpUserDetails = authentication.principal as? DcmpUserDetails
            ?: throw UnexpectedStateException("An unexpected error occurred during login process",
                ErrorCode.EXPECTATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR)

        return LoginResponse(
            userId = dcmpUserDetails.userId,
            username = dcmpUserDetails.username,
            email = dcmpUserDetails.email,
            accessToken = "",
            refreshToken = ""
        )
    }


}

