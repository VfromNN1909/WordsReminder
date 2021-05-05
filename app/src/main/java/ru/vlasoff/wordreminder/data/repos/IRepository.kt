package ru.vlasoff.wordreminder.data.repos

import kotlinx.coroutines.flow.Flow
import ru.vlasoff.wordreminder.domain.entities.Word

interface IRepository {

    suspend fun insertWord(word: Word)
    fun getAllWords(): Flow<List<Word>>
    suspend fun deleteWord(word: Word)
}