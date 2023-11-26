package io.github.eendroroy.exposed.demo.persistence.user

import io.github.eendroroy.exposed.demo.persistence.role.UserRole
import io.github.eendroroy.exposed.demo.persistence.role.UserRoles
import io.github.eendroroy.exposed.demo.persistence.task.Task
import io.github.eendroroy.exposed.demo.persistence.task.Tasks
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    var userName by Users.userName
    var email by Users.email
    var mobileNumber by Users.mobileNumber

    var tokenHash by Users.tokenHash
    var tokenCreatedAt by Users.tokenCreatedAt
    var tokenValidTill by Users.tokenValidTill

    val roles by UserRole referrersOn UserRoles.user
    val tasks by Task referrersOn Tasks.user

    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt
}
