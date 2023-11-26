package io.github.eendroroy.exposed.demo.persistence.role

import io.github.eendroroy.exposed.demo.persistence.user.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserRole(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserRole>(UserRoles)

    var role by UserRoles.role
    var user by User referencedOn UserRoles.user

    var createdAt by UserRoles.createdAt
    var updatedAt by UserRoles.updatedAt
}
