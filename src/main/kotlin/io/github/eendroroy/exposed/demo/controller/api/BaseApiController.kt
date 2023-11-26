package io.github.eendroroy.exposed.demo.controller.api

import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.security.model.UserDetail
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.security.Principal

open class BaseApiController {
    @Autowired
    private lateinit var httpRequest: HttpServletRequest

    fun agent(): String? = httpRequest.getHeader(HttpHeaders.USER_AGENT)
    fun token(): String? = httpRequest.getHeader(HttpHeaders.AUTHORIZATION).ifBlank { null }

    fun currentUser(): User {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        return (auth.principal as UserDetail).user
    }
}
