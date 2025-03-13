package org.dcmp.application.command_handler

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LogoutCommand
import org.dcmp.domain.contracts.RequestHandler
import org.springframework.stereotype.Component

@Component
class LogoutHandler: RequestHandler<LogoutCommand, LoginResponse> {
    override fun handle(request: LogoutCommand): LoginResponse {
        return LoginResponse(null, "", "", "", "");
    }
}
