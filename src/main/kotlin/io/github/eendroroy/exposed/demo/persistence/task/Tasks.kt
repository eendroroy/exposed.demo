package io.github.eendroroy.exposed.demo.persistence.task

import io.github.eendroroy.exposed.demo.persistence.core.BaseLongIdTable
import io.github.eendroroy.exposed.demo.persistence.user.Users
import org.jetbrains.exposed.sql.javatime.datetime

object Tasks : BaseLongIdTable("tasks") {
    val title = text("title").uniqueIndex()
    val description = text("description")
    val dueAt = datetime("due_at")
    val completedAt = datetime("completed_at").nullable()

    val user = reference("user", Users)
}
