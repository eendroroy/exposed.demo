package io.github.eendroroy.exposed.demo.security.filter

import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.eendroroy.exposed.demo.security.config.CustomJwtClaims
import io.github.eendroroy.exposed.demo.security.service.impl.UserDetailsServiceImpl
import io.github.eendroroy.exposed.demo.util.JwtTokenUtil
import io.github.eendroroy.exposed.demo.util.getRoles
import io.github.eendroroy.exposed.demo.util.getUserName
import io.github.eendroroy.exposed.demo.util.md5
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsServiceImpl,
) : OncePerRequestFilter() {
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        with(request.servletPath) {
            EXCLUDED_PATHS.forEach { if (this.startsWith(it)) return true }
        }
        return false
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (!requestTokenHeader.isNullOrBlank()) {
            try {
                setAuthentication(jwtTokenUtil.decodedToken(requestTokenHeader), request, requestTokenHeader)
            } catch (ex: JWTDecodeException) {
                LOGGER.error("INVALID_TOKEN {}", requestTokenHeader)
                LOGGER.error(ex.localizedMessage)
            } catch (ex: JWTVerificationException) {
                LOGGER.error("INVALID_TOKEN {}", requestTokenHeader)
                LOGGER.error(ex.localizedMessage)
            } catch (ex: Exception) {
                LOGGER.error("TOKEN_ERROR {}", requestTokenHeader)
                LOGGER.error(ex.localizedMessage, ex)
            }
        } else {
            LOGGER.warn("Token not provided")
        }

        chain.doFilter(request, response)
    }

    private fun setAuthentication(decodedToken: DecodedJWT, request: HttpServletRequest, token: String) {
        if (SecurityContextHolder.getContext().authentication == null) {
            try {
                val userDetails = userDetailsService.loadUserByUsername(decodedToken.getUserName())

                if (userDetails.user.tokenHash != token.md5()) {
                    LOGGER.error("Token hash did not match")
                    return
                }

                if (checkValidAndSetAttributes(decodedToken, request)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )

                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
//            } catch (ex: UserNotEnabledException) {
//                LOGGER.error(ex.localizedMessage, ex)
//            } catch (ex: UserNotFoundException) {
//                LOGGER.error(ex.localizedMessage, ex)
            } catch (ex: Exception) {
                LOGGER.error(ex.localizedMessage, ex)
            }
        }
    }

    private fun checkValidAndSetAttributes(decodedToken: DecodedJWT, request: HttpServletRequest): Boolean {
        request.setAttribute(CustomJwtClaims.Headers.JWT_ID, decodedToken.id)
        request.setAttribute(CustomJwtClaims.Headers.SUBJECT, decodedToken.subject)
        request.setAttribute(CustomJwtClaims.Headers.ISSUER, decodedToken.issuer)
        request.setAttribute(CustomJwtClaims.Headers.AUDIENCE, decodedToken.audience)
        request.setAttribute(CustomJwtClaims.Headers.USER_NAME, decodedToken.getUserName())
        request.setAttribute(CustomJwtClaims.Headers.ROLES, decodedToken.getRoles())
        return true
    }

    companion object {
        private val EXCLUDED_PATHS = listOf(
            "/api/v1/token/create"
        )

        private val LOGGER = LoggerFactory.getLogger(RequestAuthenticationFilter::class.java)
    }
}
