package org.dcmp.application.command_handler

import org.dcmp.application.command.RefreshTokenCommand
import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.cqrs.RequestHandler
import org.springframework.stereotype.Component

@Component
class RefreshTokenHandler: RequestHandler<RefreshTokenCommand, LoginResponse> {

    override fun handle(request: RefreshTokenCommand): LoginResponse {
        throw  Exception("Not implemented");
    }

}
