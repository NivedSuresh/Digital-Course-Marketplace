package org.dcmp.application.service

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.application.command.LogoutCommand
import org.dcmp.application.command_handler.LoginHandler
import org.dcmp.application.command_handler.LogoutHandler
import org.dcmp.application.command_handler.RefreshTokenHandler
import org.dcmp.domain.contracts.service.IAuthService
import org.springframework.stereotype.Service

@Service
class AuthService(val loginHandler: LoginHandler,
                  val logoutHandler: LogoutHandler,
                  val refreshTokenHandler: RefreshTokenHandler,
): IAuthService {

    override fun login(loginRequest: LoginCommand): LoginResponse {
        return loginHandler.handle(loginRequest);
    }

    override fun logout(logoutCommand: LogoutCommand): LoginResponse {
        return logoutHandler.handle(logoutCommand);
    }

    override fun refreshToken(refreshToken: String): LoginResponse {

    }


}
