package org.dcmp.application.query

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.cqrs.Request

data class LoginQuery(val email: String, val password: String) : Request<LoginResponse>

