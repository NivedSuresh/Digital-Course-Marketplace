package org.dcmp.application.command

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.cqrs.Request

class LogoutCommand: Request<LoginResponse> {
}
