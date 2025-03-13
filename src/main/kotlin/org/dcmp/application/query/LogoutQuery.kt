package org.dcmp.application.query

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.cqrs.Request

class LogoutQuery: Request<LoginResponse> {
}
