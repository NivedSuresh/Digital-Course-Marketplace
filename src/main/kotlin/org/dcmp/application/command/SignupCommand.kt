package org.dcmp.application.command

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Role

data class SignupCommand(val username: String, val email: String, val password: String, val role: Role): Request<LoginResponse>
