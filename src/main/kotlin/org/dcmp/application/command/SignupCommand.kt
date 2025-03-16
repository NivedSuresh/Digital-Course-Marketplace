package org.dcmp.application.command

import org.dcmp.application.dto.LoginResponse
import org.dcmp.domain.contracts.Request
import org.dcmp.domain.entity.Role

import jakarta.validation.constraints.*

data class SignupCommand(
    @field:NotBlank(message = "Username cannot be blank")
    @field:Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    val username: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Invalid email format")
    val email: String,

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    var password: String,

    @field:NotNull(message = "Role must be specified")
    val role: Role
) : Request<LoginResponse>
