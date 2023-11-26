package io.github.eendroroy.exposed.demo.security.service.impl

import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.persistence.user.Users
import io.github.eendroroy.exposed.demo.security.model.UserDetail
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetail {
        return User.find { Users.userName eq username }.firstOrNull()?.let { UserDetail(it) }
            ?: throw RuntimeException()
    }
}
