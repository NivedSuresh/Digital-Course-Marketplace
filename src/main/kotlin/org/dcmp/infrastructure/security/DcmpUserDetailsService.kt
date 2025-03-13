package org.dcmp.infrastructure.security

import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.dcmp.domain.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class DcmpUserDetailsService(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository.findByEmail(email!!).orElseThrow { UserNotFoundException("User not found with the email: $email") }
        return DcmpUserDetails(user.email, user.hashedPassword, user.authority, user.userId!!)
    }

}
