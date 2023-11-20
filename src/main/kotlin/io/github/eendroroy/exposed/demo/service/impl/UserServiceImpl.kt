package io.github.eendroroy.exposed.demo.service.impl

import io.github.eendroroy.exposed.demo.config.Role
import io.github.eendroroy.exposed.demo.dto.request.RegistrationRequest
import io.github.eendroroy.exposed.demo.persistence.role.UserRole
import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl : UserService {
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
}
