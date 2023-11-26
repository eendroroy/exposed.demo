package io.github.eendroroy.exposed.demo.persistence.task

import io.github.eendroroy.exposed.demo.persistence.core.BaseLongEntity
import io.github.eendroroy.exposed.demo.persistence.user.User
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Task(id: EntityID<Long>) : BaseLongEntity(id, Tasks) {
    companion object : LongEntityClass<Task>(Tasks)

    var title by Tasks.title
    var description by Tasks.description
    var dueAt by Tasks.dueAt
    var completedAt by Tasks.completedAt

    var user by User referencedOn Tasks.user
}
