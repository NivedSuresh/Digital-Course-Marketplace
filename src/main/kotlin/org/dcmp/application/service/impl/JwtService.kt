package org.dcmp.application.service.impl

import org.dcmp.application.service.IJwtService
import org.springframework.stereotype.Service
import java.time.Instant


@Service
class JwtService: IJwtService {

    override fun generateToken(identifier: String, authority: String, expiry: Instant): String {
        return "";
    }
}
