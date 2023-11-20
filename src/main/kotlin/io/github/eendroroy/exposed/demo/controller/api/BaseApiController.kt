package io.github.eendroroy.exposed.demo.controller.api

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders

open class BaseApiController {
    @Autowired
    private lateinit var httpRequest: HttpServletRequest

    fun agent(): String? = httpRequest.getHeader(HttpHeaders.USER_AGENT)
    fun token(): String? = httpRequest.getHeader(HttpHeaders.AUTHORIZATION).ifBlank { null }

}
