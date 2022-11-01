package com.bifidokk.repository

import com.bifidokk.entity.UserEntity
import com.bifidokk.service.user.User
import org.ktorm.database.Database
import org.ktorm.dsl.*

class UserRepository(private val db: Database) {
    fun findUserByEmail(email: String): User? {
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

    fun findUserById(id: Int): User? {
        return db.from(UserEntity)
            .select()
            .where(UserEntity.id eq id)
            .map {
                User(
                    id = it[UserEntity.id],
                    email = it[UserEntity.email].orEmpty()
                )
            }
            .firstOrNull()
    }
}