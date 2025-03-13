package org.dcmp.api.utils

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.dcmp.application.dto.LoginResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CookieUtil {

    @Value("\${bainsight.domain:localhost}")
    private lateinit var domain: String

    @Value("\${bainsight.same-site:Lax}")
    private lateinit var sameSite: String

    fun createCookie(name: String, value: String, path: String): Cookie {
        return Cookie(name, value).apply {
            this.path = path
            this.isHttpOnly = true
            this.maxAge = Duration.ofHours(12).seconds.toInt()
            this.secure = true
            this.domain = this@CookieUtil.domain
        }
    }

    fun appendCookies(response: HttpServletResponse, loginResponse: LoginResponse) {
        val accessCookie = this.createCookie("ACCESS_TOKEN", loginResponse.accessToken, "/")
        val refreshCookie = this.createCookie("REFRESH_TOKEN", loginResponse.refreshToken, "/api/bainsight/auth/refresh")

        response.addCookie(accessCookie)
        response.addCookie(refreshCookie)
    }
}

