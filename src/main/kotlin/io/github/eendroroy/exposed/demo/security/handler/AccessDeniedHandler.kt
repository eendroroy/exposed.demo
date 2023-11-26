package io.github.eendroroy.exposed.demo.security.handler

import io.github.eendroroy.exposed.demo.util.forbiddenResponse
import io.github.eendroroy.exposed.demo.util.toJson
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
class AccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        response.status = HttpServletResponse.SC_FORBIDDEN

        response.writer.write(forbiddenResponse().toJson() ?: "")
        response.writer.flush()
        response.writer.close()
    }
}
