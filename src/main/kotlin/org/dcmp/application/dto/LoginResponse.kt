package org.dcmp.application.dto

data class LoginResponse
(
    val userId: Long,
    val username: String,
    val email: String,
    val accessToken: String,
    val refreshToken: String
)

