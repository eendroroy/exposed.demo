package io.github.eendroroy.exposed.demo.persistence.user

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Users : LongIdTable("user") {
    val userName = varchar("user_name", length = 4_000).uniqueIndex()
    val email = varchar("email", length = 4_000).uniqueIndex()
    val mobileNumber = varchar("mobile_number", length = 10)
    val tokenHash = text("token_hash").nullable()
    val tokenCreatedAt = datetime("token_created_at").nullable()
    val tokenValidTill = datetime("token_valid_till").nullable()
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
