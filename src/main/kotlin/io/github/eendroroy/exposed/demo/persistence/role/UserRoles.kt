package io.github.eendroroy.exposed.demo.persistence.role

import io.github.eendroroy.exposed.demo.persistence.user.Users
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object UserRoles : LongIdTable("user_roles") {
    val role = text("role", eagerLoading = true)
    val user = reference("user", Users)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    init {
        uniqueIndex(role, user)
    }
}
