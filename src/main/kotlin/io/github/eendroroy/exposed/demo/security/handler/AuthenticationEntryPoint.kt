package io.github.eendroroy.exposed.demo.security.handler

import io.github.eendroroy.exposed.demo.util.toJson
import io.github.eendroroy.exposed.demo.util.unauthorizedResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.Serializable

class AuthenticationEntryPoint : AuthenticationEntryPoint, Serializable {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, ex: AuthenticationException) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        response.writer.write(unauthorizedResponse(ex.localizedMessage).toJson() ?: "")
        response.writer.flush()
        response.writer.close()
    }
}
