package org.dcmp.application.service

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.application.command.LogoutCommand
import org.dcmp.application.command.SignupCommand

interface IAuthService {
    /**
     * Authenticates the user using the provided credentials.
     *
     * - On successful authentication, returns the user's information along with new access and refresh tokens.
     * - Tokens are sent as **HTTP-only cookies**, making them inaccessible to client-side JavaScript.
     *
     * @param loginRequest The login query containing user credentials.
     * @return LoginResponse containing user details and token metadata.
     */
    fun login(loginRequest: LoginCommand): LoginResponse

    /**
     * Logs out the user by invalidating the current session.
     *
     * - Sets empty values for both access and refresh tokens, sent as **HTTP-only cookies**.
     * - This ensures that existing tokens are overwritten and cannot be used further.
     *
     * @param logoutCommand The logout query containing user session details.
     * @return LoginResponse with empty token values.
     */
    fun logout(logoutCommand: LogoutCommand): LoginResponse


    fun signup(signupCommand: SignupCommand): LoginResponse
}
