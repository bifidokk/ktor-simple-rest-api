package com.bifidokk.repository

import com.bifidokk.entity.UserEntity
import com.bifidokk.service.User
import org.ktorm.database.Database
import org.ktorm.dsl.*

class UserRepository(private val db: Database) {
    fun getUserByEmail(email: String): User? {
        return db.from(UserEntity)
            .select()
            .where(UserEntity.email eq email)
            .map {
                User(
                    id = it[UserEntity.id],
                    email = it[UserEntity.email].orEmpty()
                )
            }
            .firstOrNull()
    }
}