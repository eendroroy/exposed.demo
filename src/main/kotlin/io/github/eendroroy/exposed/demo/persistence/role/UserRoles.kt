package io.github.eendroroy.exposed.demo.persistence.role

import io.github.eendroroy.exposed.demo.persistence.core.BaseLongIdTable
import io.github.eendroroy.exposed.demo.persistence.user.Users
import org.jetbrains.exposed.dao.EntityChangeType
import org.jetbrains.exposed.dao.EntityHook
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.toEntity
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object UserRoles : BaseLongIdTable("user_roles") {
    val role = text("role", eagerLoading = true)
    val user = reference("user", Users)

    init {
        uniqueIndex(role, user)
    }
}
