package io.github.eendroroy.exposed.demo.util

import com.auth0.jwt.interfaces.DecodedJWT
import io.github.eendroroy.exposed.demo.security.config.CustomJwtClaims

fun DecodedJWT.getUserName(): String {
    return this.getClaim(CustomJwtClaims.USER_NAME).asString()
}

fun DecodedJWT.getRoles(): List<String> {
    return this.getClaim(CustomJwtClaims.ROLES).asList(String::class.java)
}
