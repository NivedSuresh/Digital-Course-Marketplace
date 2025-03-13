package org.dcmp.application.command_handler

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.application.service.IJwtService
import org.dcmp.domain.exception.ErrorCode
import org.dcmp.infrastructure.security.DcmpUserDetails
import org.dcmp.api.advice.UnexpectedStateException
import org.springframework.context.event.EventListener
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit


@Component
class LoginHandler(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: IJwtService
) : RequestHandler<LoginCommand, LoginResponse> {


    override fun handle(request: LoginCommand): LoginResponse {
        val token = UsernamePasswordAuthenticationToken(request.email, request.password)
        val authentication = authenticationManager.authenticate(token)

        val dcmpUserDetails = authentication.principal as? DcmpUserDetails
            ?: throw UnexpectedStateException("An unexpected error occurred during login process",
                ErrorCode.EXPECTATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR)

        val accessToken = jwtService.generateToken(dcmpUserDetails.userId.toString(), dcmpUserDetails.authority.name,  Instant.now().plus(30,  ChronoUnit.MINUTES))
        val refreshToken = jwtService.generateToken(dcmpUserDetails.userId.toString(), dcmpUserDetails.authority.name,  Instant.now().plus(12,  ChronoUnit.HOURS))

        return LoginResponse(
            userId = dcmpUserDetails.userId,
            username = dcmpUserDetails.username,
            email = dcmpUserDetails.email,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }


}

