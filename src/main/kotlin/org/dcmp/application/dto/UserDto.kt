package org.dcmp.application.dto

import org.dcmp.domain.entity.Role

data class UserDTO(
    val id: Long,
    val username: String,
    val email: String,
    val authority: Role
)

