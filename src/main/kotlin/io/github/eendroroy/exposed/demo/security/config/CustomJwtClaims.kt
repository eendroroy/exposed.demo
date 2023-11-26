package io.github.eendroroy.exposed.demo.security.config

object CustomJwtClaims {
    const val USER_NAME = "USER_NAME"
    const val ROLES = "ROLES"

    object Headers {
        const val JWT_ID = "token-id"
        const val SUBJECT = "token-subject"
        const val ISSUER = "token-issuer"
        const val AUDIENCE = "token-audience"
        const val USER_NAME = "token-username"
        const val ROLES = "token-role"
    }
}
