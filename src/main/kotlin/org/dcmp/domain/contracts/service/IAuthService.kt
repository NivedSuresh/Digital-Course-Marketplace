package org.dcmp.domain.contracts.service

import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.command.LoginCommand
import org.dcmp.application.command.LogoutCommand

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

    /**
     * Refreshes the access token using a valid refresh token.
     *
     * - Generates a new access token while issuing a fresh refresh token.
     * - Tokens are sent as **HTTP-only cookies** to maintain security.
     * - If the refresh token is invalid or expired, an authentication error is thrown.
     *
     * @param refreshToken The refresh token used to generate new tokens.
     * @return LoginResponse containing updated user details and new tokens.
     */
    fun refreshToken(refreshToken: String): LoginResponse
}
