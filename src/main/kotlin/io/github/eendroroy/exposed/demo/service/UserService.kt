package io.github.eendroroy.exposed.demo.service

import io.github.eendroroy.exposed.demo.dto.request.RegistrationRequest
import io.github.eendroroy.exposed.demo.persistence.user.User

interface UserService {
    fun register(request: RegistrationRequest): User
}
