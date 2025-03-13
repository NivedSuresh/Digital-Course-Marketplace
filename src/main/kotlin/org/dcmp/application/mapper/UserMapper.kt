package org.dcmp.application.mapper

import org.dcmp.application.command.SignupCommand
import org.dcmp.domain.entity.User

class UserMapper {

    companion object {
        fun toEntity(signupCommand: SignupCommand): User {
            return User(null, signupCommand.username, signupCommand.email, signupCommand.password, signupCommand.role);
        }
    }
}
