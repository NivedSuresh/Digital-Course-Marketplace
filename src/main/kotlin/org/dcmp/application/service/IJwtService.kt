package org.dcmp.application.service

import java.time.Instant

interface IJwtService {
    fun generateToken(identifier: String, authority: String, expiry: Instant): String
}
