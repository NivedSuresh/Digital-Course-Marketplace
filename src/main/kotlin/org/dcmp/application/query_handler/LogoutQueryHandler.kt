package org.dcmp.application.query_handler

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.query.LogoutQuery
import org.dcmp.domain.contracts.cqrs.RequestHandler
import org.springframework.stereotype.Component

@Component
class LogoutQueryHandler: RequestHandler<LogoutQuery, LoginResponse> {
    override fun handle(request: LogoutQuery): LoginResponse {
        throw Exception("Not implemented");
    }
}
