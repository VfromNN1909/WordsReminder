package ru.vlasoff.wordreminder.domain.usecases

import ru.vlasoff.wordreminder.data.repos.LocalStorageRepository
import ru.vlasoff.wordreminder.domain.entities.Word
import javax.inject.Inject

class InsertWordUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {
    suspend fun execute(word: Word) {
        repository.insertWord(word)
    }
}