package io.github.eendroroy.exposed.demo.persistence.role

import io.github.eendroroy.exposed.demo.persistence.core.BaseLongEntity
import io.github.eendroroy.exposed.demo.persistence.core.BaseLongEntityClass
import io.github.eendroroy.exposed.demo.persistence.user.User
import io.github.eendroroy.exposed.demo.persistence.user.Users
import org.jetbrains.exposed.dao.id.EntityID

class UserRole(id: EntityID<Long>) : BaseLongEntity(id, Users) {
    companion object : BaseLongEntityClass<UserRole>(UserRoles)
    var role by UserRoles.role
    var user by User referencedOn UserRoles.user
}
