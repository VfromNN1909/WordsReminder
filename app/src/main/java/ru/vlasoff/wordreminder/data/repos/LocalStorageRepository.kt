package ru.vlasoff.wordreminder.data.repos

import kotlinx.coroutines.flow.distinctUntilChanged
import ru.vlasoff.wordreminder.data.db.WordsDao
import ru.vlasoff.wordreminder.domain.entities.Word
import javax.inject.Inject

class LocalStorageRepository @Inject constructor(
    private val dao: WordsDao
) : IRepository {

    override suspend fun insertWord(word: Word) = dao.insertWord(word)

    override fun getAllWords() = dao.getAllWords().distinctUntilChanged()

    override suspend fun deleteWord(word: Word) = dao.deleteWord(word)

}