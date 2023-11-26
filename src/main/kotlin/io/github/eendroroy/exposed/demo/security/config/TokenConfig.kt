package io.github.eendroroy.exposed.demo.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated

@Validated
@Configuration
@ConfigurationProperties(prefix = "auth.token")
class TokenConfig {
    lateinit var secret: String
    lateinit var issuer: String
    lateinit var subject: String
    lateinit var audience: String
    var validityMinutes: Long = 1_440 // 1 day
}
