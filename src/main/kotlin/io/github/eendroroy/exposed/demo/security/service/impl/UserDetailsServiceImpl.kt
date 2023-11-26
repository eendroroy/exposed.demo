package io.github.eendroroy.exposed.demo.security.service.impl

import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.persistence.user.Users
import io.github.eendroroy.exposed.demo.security.model.UserDetail
import org.jetbrains.exposed.dao.with
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String): UserDetail {
        return User.find { Users.userName eq username }.with(User::roles).firstOrNull()
            ?.let { UserDetail(it, it.roles.toList()) }
            ?: throw RuntimeException()
    }
}
