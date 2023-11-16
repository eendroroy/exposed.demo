package io.github.eendroroy.exposed.demo.persistence.user

import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable("user") {
    val userName = varchar("user_name", length = 4_000).uniqueIndex()
    val email = varchar("email", length = 4_000).uniqueIndex()
    val mobileNumber = varchar("mobile_number", length = 10)
}
