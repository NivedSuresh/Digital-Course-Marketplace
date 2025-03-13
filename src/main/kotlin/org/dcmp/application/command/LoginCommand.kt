package org.dcmp.application.command

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.Request

data class LoginCommand(val email: String, val password: String) : Request<LoginResponse>

