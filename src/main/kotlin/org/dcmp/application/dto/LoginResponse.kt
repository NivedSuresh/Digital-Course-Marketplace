package org.dcmp.application.dto

data class LoginResponse
(
    val id: Long,
    val name: String,
    val email: String,
    val accessToken: String,
    val refreshToken: String
)

