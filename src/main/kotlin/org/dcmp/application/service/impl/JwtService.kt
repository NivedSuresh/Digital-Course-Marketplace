package org.dcmp.application.service.impl

import org.dcmp.application.service.IJwtService
import org.slf4j.LoggerFactory
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant


@Service
class JwtService(private val jwtEncoder: JwtEncoder): IJwtService {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun generateToken(identifier: String, authority: String, expiry: Instant): String {
        log.debug("Jwt generate method has been triggered!")

        val claimsSet = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(expiry)
            .issuer("self")
            .subject(identifier)
            .claim("authority", authority)
            .build()

        val token = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).tokenValue

        log.debug("String jwt has been returned from generateJwt()")
        return token
    }

}
