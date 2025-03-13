package org.dcmp.application.command

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.Request

class RefreshTokenCommand(val refreshToken: String): Request<LoginResponse>
