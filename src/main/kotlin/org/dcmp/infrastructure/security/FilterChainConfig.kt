package org.dcmp.infrastructure.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class FilterChainConfig(private val authenticationManager: AuthenticationManager) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }

        http.oauth2ResourceServer { oauth2Config -> oauth2Config.jwt { } }

        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(HttpMethod.POST, "/login", "/signup", "/error").permitAll()
                .anyRequest().authenticated()
        }

        http.authenticationManager(authenticationManager)

        return http.build()
    }

}
