package org.dcmp.infrastructure.security

import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityBeansConfig(private val authenticationManager: AuthenticationManager,
                          private val rsaKeys: RsaKeyProperties) {

    @Bean
    fun bcryptPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() };

        http.oauth2ResourceServer {oauth2Config -> oauth2Config.jwt {  }}

        http.sessionManagement {s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS) };

        http.authorizeHttpRequests {auth -> auth.requestMatchers("/logout").hasAnyAuthority("ADMIN", "CREATOR", "CUSTOMER")}
            .authorizeHttpRequests {auth -> auth.requestMatchers("/login",  "/signup").permitAll()}
            .authorizeHttpRequests {auth -> auth.anyRequest().authenticated()};

        http.authenticationManager(authenticationManager);

        return http.build();
    }


    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey).build();
    }


    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk: JWK = RSAKey.Builder(rsaKeys.publicKey).privateKey(rsaKeys.privateKey).build()
        val source: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(source)
    }


}
