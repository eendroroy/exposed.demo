package io.github.eendroroy.exposed.demo.util

import com.auth0.jwt.JWT
import com.auth0.jwt.RegisteredClaims
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.eendroroy.exposed.demo.security.config.CustomJwtClaims
import io.github.eendroroy.exposed.demo.security.config.TokenConfig
import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Component
class JwtTokenUtil(private val config: TokenConfig) : Serializable {
    private val algorithm: Algorithm = Algorithm.HMAC256(config.secret)

    fun createToken(userName: String, roles: List<String>): Pair<String, LocalDateTime> {
        val expiresAt = expiresAt()
        return JWT.create()
            .withClaim(RegisteredClaims.ISSUER, config.issuer)
            .withClaim(RegisteredClaims.SUBJECT, config.subject)
            .withClaim(RegisteredClaims.ISSUED_AT, Date())
            .withClaim(RegisteredClaims.NOT_BEFORE, Date())
            .withClaim(RegisteredClaims.EXPIRES_AT, expiresAt.atZone(ZoneId.systemDefault()).toInstant())
            .withClaim(RegisteredClaims.AUDIENCE, config.audience)
            .withClaim(RegisteredClaims.JWT_ID, System.currentTimeMillis())
            .withClaim(CustomJwtClaims.USER_NAME, userName)
            .withClaim(CustomJwtClaims.ROLES, roles)
            .sign(algorithm).let {
                Pair(it, expiresAt)
            }
    }

    fun decodedToken(token: String): DecodedJWT {
        val verifier = JWT.require(algorithm).withIssuer(config.issuer).build()
        return verifier.verify(token)
    }

    private fun expiresAt(): LocalDateTime {
        return LocalDateTime.now().plusMinutes(config.validityMinutes)
    }
}
