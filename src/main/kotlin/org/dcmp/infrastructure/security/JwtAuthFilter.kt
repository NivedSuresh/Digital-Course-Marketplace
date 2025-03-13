package org.dcmp.infrastructure.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class JwtAuthFilter(private val jwtDecoder: JwtDecoder) : OncePerRequestFilter() {

    companion object {
        private val ignorablePaths = setOf("/api/v1/login", "/api/v1/logout", "/api/v1/signup")
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val path = request.requestURI
        if (ignorablePaths.contains(path)) {
            filterChain.doFilter(request, response)
            return
        }
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
        val userId = jwt.subject
        val roles = jwt.getClaimAsStringList("authority") ?: listOf()

        val authorities = roles.map { SimpleGrantedAuthority(it) }
        val principal = User(userId, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, null, authorities)
    }

}

