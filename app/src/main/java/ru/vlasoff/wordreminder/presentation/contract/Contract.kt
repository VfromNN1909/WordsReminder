package ru.vlasoff.wordreminder.presentation.contract

import androidx.lifecycle.LiveData
import ru.vlasoff.wordreminder.domain.entities.Word

interface Contract {
    interface IAllWordsViewModel {
        val words: LiveData<MutableList<Word>>
        fun insert(word: Word)
        fun getAllWords()
    }
}