package org.dcmp.infrastructure.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityBeansConfig(private val authenticationManager: AuthenticationManager,) {

    @Bean
    fun bcryptPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() };
        http.sessionManagement {s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS) };

        http.authorizeHttpRequests {auth -> auth.requestMatchers("/logout").hasAnyAuthority("ADMIN", "CREATOR", "CUSTOMER")}
            .authorizeHttpRequests {auth -> auth.requestMatchers("/login",  "/signup").permitAll()}
            .authorizeHttpRequests {auth -> auth.anyRequest().authenticated()};

        http.authenticationManager(authenticationManager);

        return http.build();
    }


}
