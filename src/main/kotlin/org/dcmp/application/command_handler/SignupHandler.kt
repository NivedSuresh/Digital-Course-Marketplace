package org.dcmp.application.command_handler


import org.dcmp.application.command.SignupCommand
import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.mapper.UserMapper
import org.dcmp.application.service.impl.JwtService
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.domain.exception.EntityAlreadyExistsException
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class SignupHandler(private val userRepository: UserRepository,
                    private val jwtService: JwtService,
                    private val passwordEncoder: PasswordEncoder): RequestHandler<SignupCommand, LoginResponse> {

    override fun handle(request: SignupCommand): LoginResponse {

        request.password = passwordEncoder.encode(request.password);
        val userEntity = UserMapper.toEntity(request)

        if (this.userRepository.existsByEmail(userEntity.email)) {
            throw EntityAlreadyExistsException("User")
        }

        val user = this.userRepository.save(userEntity);

        val accessToken = jwtService.generateToken(user.id.toString(), user.authority.name,  Instant.now().plus(30,  ChronoUnit.MINUTES))
        val refreshToken = jwtService.generateToken(user.id.toString(), user.authority.name,  Instant.now().plus(12,  ChronoUnit.HOURS))


        return LoginResponse(user.id, user.username, user.email, accessToken, refreshToken);
    }

}
