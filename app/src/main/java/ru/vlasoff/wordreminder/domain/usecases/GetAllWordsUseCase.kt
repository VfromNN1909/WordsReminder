package ru.vlasoff.wordreminder.domain.usecases

import ru.vlasoff.wordreminder.data.repos.LocalStorageRepository
import javax.inject.Inject

class GetAllWordsUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {
    fun execute() = repository.getAllWords()
}