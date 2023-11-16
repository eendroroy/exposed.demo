package io.github.eendroroy.exposed.demo.persistence.role

import io.github.eendroroy.exposed.demo.persistence.user.Users
import org.jetbrains.exposed.dao.id.LongIdTable

object UserRoles : LongIdTable("user_roles") {
    val role = text("role", eagerLoading = true)
    val user = reference("user", Users)

    init {
        uniqueIndex(role, user)
    }
}
