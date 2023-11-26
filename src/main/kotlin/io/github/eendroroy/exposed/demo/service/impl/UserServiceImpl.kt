package io.github.eendroroy.exposed.demo.service.impl

import io.github.eendroroy.exposed.demo.config.Role
import io.github.eendroroy.exposed.demo.dto.request.RegistrationRequest
import io.github.eendroroy.exposed.demo.dto.request.TokenRequest
import io.github.eendroroy.exposed.demo.persistence.role.UserRole
import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.persistence.user.Users
import io.github.eendroroy.exposed.demo.service.UserService
import io.github.eendroroy.exposed.demo.util.JwtTokenUtil
import io.github.eendroroy.exposed.demo.util.md5
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserServiceImpl(private val tokenUtil: JwtTokenUtil) : UserService {
    @Transactional
    override fun register(request: RegistrationRequest): User {
        val user = User.new {
            this.userName = request.userName
            this.email = request.email
            this.mobileNumber = request.mobileNumber
        }

        UserRole.new {
            this.role = Role.USER.name
            this.user = user
        }

        return user
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun createToken(request: TokenRequest): String {
        val user = User.find { Users.userName eq request.userName }.firstOrNull() ?: throw RuntimeException()

        val (token, validTill) = tokenUtil.createToken(user.userName, user.roles.map { it.role })

        user.tokenHash = token.md5()
        user.tokenCreatedAt = LocalDateTime.now()
        user.tokenValidTill = validTill

        return token
    }
}
