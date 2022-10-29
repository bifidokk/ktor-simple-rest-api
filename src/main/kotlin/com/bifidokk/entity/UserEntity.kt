package com.bifidokk.entity

import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserEntity: Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val email = varchar("email")
    val createdAt = datetime("created_at")
}