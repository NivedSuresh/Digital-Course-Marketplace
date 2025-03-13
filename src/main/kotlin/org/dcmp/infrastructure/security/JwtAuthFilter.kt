package org.dcmp.infrastructure.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Component


@Component
class JwtAuthFilter(private val jwtDecoder: JwtDecoder) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = extractToken(request)

        if (token != null) {
            try {
                val jwt: Jwt = jwtDecoder.decode(token)
                val authentication = getAuthentication(jwt)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (ex: JwtException) {
                SecurityContextHolder.clearContext()
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        return request.cookies
            ?.find { it.name == "ACCESS_TOKEN" }
            ?.value
    }


    private fun getAuthentication(jwt: Jwt): UsernamePasswordAuthenticationToken {
        val username = jwt.subject
        val roles = jwt.getClaimAsStringList("roles") ?: listOf()

        val authorities = roles.map { org.springframework.security.core.authority.SimpleGrantedAuthority(it) }
        val principal = User(username, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, null, authorities)
    }

}

