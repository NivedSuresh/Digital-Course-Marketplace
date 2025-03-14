package org.dcmp.infrastructure.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class FilterChainConfig(private val authenticationManager: AuthenticationManager,
                        private val jwtAuthFilter: JwtAuthFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }

        http.oauth2ResourceServer { oauth2Config -> oauth2Config.jwt { } }

        http.sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.authorizeHttpRequests { it
            .requestMatchers("/login", "/logout", "/signup").permitAll()
            .requestMatchers(HttpMethod.GET, "/course").authenticated()
            .requestMatchers(HttpMethod.GET, "/user").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.GET, "/stats").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/customer/buy/course/*").hasAuthority("CUSTOMER")
            .requestMatchers(HttpMethod.GET, "/creator/course").hasAnyAuthority("CREATOR")
            .requestMatchers(HttpMethod.POST, "/creator/course").hasAnyAuthority("CREATOR", "ADMIN") }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.authenticationManager(authenticationManager)

        return http.build()
    }

}

