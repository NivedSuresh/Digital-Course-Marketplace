package org.dcmp.application.service

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.query.LoginQuery
import org.dcmp.application.query.LogoutQuery
import org.dcmp.application.query_handler.LoginHandler
import org.dcmp.application.query_handler.LogoutQueryHandler
import org.dcmp.domain.contracts.service.IAuthService
import org.springframework.stereotype.Service

@Service
class AuthService(val loginHandler: LoginHandler,
                  val logoutHandler: LogoutQueryHandler): IAuthService {

    override fun login(loginRequest: LoginQuery): LoginResponse {
        return loginHandler.handle(loginRequest);
    }

    override fun logout(logoutQuery: LogoutQuery): LoginResponse {
        return logoutHandler.handle(logoutQuery);
    }

    override fun refreshToken(refreshToken: String): LoginResponse {
        TODO("Not yet implemented")
    }


}
