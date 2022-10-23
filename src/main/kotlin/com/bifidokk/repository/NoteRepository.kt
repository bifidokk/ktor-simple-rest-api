package com.bifidokk.repository

import com.bifidokk.entity.NoteEntity
import com.bifidokk.service.Note
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select

class NoteRepository(private val db: Database) {
    fun getAllNotes(): List<Note> {
        return db.from(NoteEntity)
            .select()
            .map {Note(it[NoteEntity.id] ?: -1, it[NoteEntity.note].orEmpty()) }
    }
}