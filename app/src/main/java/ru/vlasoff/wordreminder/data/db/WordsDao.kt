package ru.vlasoff.wordreminder.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vlasoff.wordreminder.domain.entities.Word

@Dao
interface WordsDao {

    @Insert
    fun insertWord(word: Word)

    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<Word>>

    @Delete
    fun deleteWord(word: Word)
}