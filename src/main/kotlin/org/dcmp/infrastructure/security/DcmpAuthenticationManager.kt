package org.dcmp.infrastructure.security

import org.apache.logging.log4j.util.Strings
import org.dcmp.domain.exception.ErrorCode
import org.dcmp.api.advice.InvalidCredentialsException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.util.*


@Component
class DcmpAuthenticationManager(var userDetailsService: DcmpUserDetailsService,
                                var passwordEncoder: PasswordEncoder): AuthenticationManager {

   val log: Logger = LoggerFactory.getLogger(DcmpAuthenticationManager::class.java)

    override fun authenticate(authentication: Authentication): Authentication {

        val userDetails: DcmpUserDetails = userDetailsService.loadUserByUsername(authentication.name) as DcmpUserDetails
        val userEnteredPassword = Objects.toString(authentication.credentials, Strings.EMPTY)

        if (userEnteredPassword != userDetails.password) {
            log.error(
                "Invalid login attempt for the identifier : {}",
                authentication.name
            )
            throw InvalidCredentialsException(ErrorCode.INVALID_PASSWORD)
        }


        val userDetailsChecker = AccountStatusUserDetailsChecker()
        userDetailsChecker.check(userDetails)

        log.info("Successfully authenticated user {}", userDetails.username)

        return UsernamePasswordAuthenticationToken(
            userDetails,
            authentication.credentials,
            userDetails.authorities
        )

    }

}
