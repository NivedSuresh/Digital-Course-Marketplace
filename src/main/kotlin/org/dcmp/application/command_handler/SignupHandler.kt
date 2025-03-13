package org.dcmp.application.command_handler


import org.dcmp.application.command.SignupCommand
import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.mapper.UserMapper
import org.dcmp.application.service.impl.JwtService
import org.dcmp.domain.contracts.RequestHandler
import org.dcmp.infrastructure.persistence.jpa.UserRepository
import java.time.Instant
import java.time.temporal.ChronoUnit

class SignupHandler(private val userRepository: UserRepository, private val jwtService: JwtService): RequestHandler<SignupCommand, LoginResponse> {

    override fun handle(request: SignupCommand): LoginResponse {

        val userEntity = UserMapper.toEntity(request)
        val user = this.userRepository.save(userEntity);

        val accessToken = jwtService.generateToken(user.userId.toString(), user.authority.name,  Instant.now().plus(30,  ChronoUnit.MINUTES))
        val refreshToken = jwtService.generateToken(user.userId.toString(), user.authority.name,  Instant.now().plus(12,  ChronoUnit.HOURS))


        return LoginResponse(user.userId, user.username, user.email, accessToken, refreshToken);
    }

}
