package org.dcmp.application.service.impl

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.application.command.LogoutCommand
import org.dcmp.application.command.SignupCommand
import org.dcmp.application.command_handler.LoginHandler
import org.dcmp.application.command_handler.LogoutHandler
import org.dcmp.application.command_handler.SignupHandler
import org.dcmp.application.service.IAuthService
import org.springframework.stereotype.Service

@Service
class AuthService(private val loginHandler: LoginHandler,
                  private val logoutHandler: LogoutHandler,
                  private val signupHandler: SignupHandler
): IAuthService {

    override fun login(loginRequest: LoginCommand): LoginResponse {
        return loginHandler.handle(loginRequest);
    }

    override fun logout(logoutCommand: LogoutCommand): LoginResponse {
        return logoutHandler.handle(logoutCommand);
    }

    override fun signup(signupCommand: SignupCommand): LoginResponse {
        return signupHandler.handle(signupCommand);
    }


}
