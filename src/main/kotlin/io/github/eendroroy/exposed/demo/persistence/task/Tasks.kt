package io.github.eendroroy.exposed.demo.persistence.task

import io.github.eendroroy.exposed.demo.persistence.core.BaseLongIdTable
import io.github.eendroroy.exposed.demo.persistence.user.Users
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object Tasks : BaseLongIdTable("tasks") {
    val taskName = varchar("task_name", length = 4_000).uniqueIndex()
    val taskDetails = varchar("task_details", length = 4_000)
    val user = reference("user", Users)
}
