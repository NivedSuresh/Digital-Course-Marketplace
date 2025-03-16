package org.dcmp.api.controllers

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.dcmp.api.utils.CookieUtil
import org.dcmp.application.command.LoginCommand
import org.dcmp.application.command.SignupCommand
import org.dcmp.application.dto.LoginResponse
import org.dcmp.application.service.IAuthService
import org.dcmp.application.service.impl.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class AuthController(private val authService: IAuthService, private val cookieUtil: CookieUtil) {


    @PostMapping("login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun login(@RequestBody loginCommand: LoginCommand, response: HttpServletResponse): LoginResponse {
        val login = this.authService.login(loginCommand)
        this.cookieUtil.appendCookies(response, login)
        return login
    }

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody @Valid signupCommand: SignupCommand, response: HttpServletResponse): LoginResponse {
        val signup = this.authService.signup(signupCommand)
        this.cookieUtil.appendCookies(response, signup)
        return signup;
    }



}
