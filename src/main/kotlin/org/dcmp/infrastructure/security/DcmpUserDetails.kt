package org.dcmp.infrastructure.security

import org.dcmp.domain.entity.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

data class DcmpUserDetails(
    val email: String,
    val passwordValue: String,
    val authority: Role,
    val userId: Long): UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority.name)
    }

    override fun getPassword(): String {
        return passwordValue;
    }

    override fun getUsername(): String {
        return email;
    }
}
