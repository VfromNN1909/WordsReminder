package ru.vlasoff.wordreminder.presentation.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.vlasoff.wordreminder.domain.entities.Word
import ru.vlasoff.wordreminder.domain.usecases.DeleteWordUseCase
import ru.vlasoff.wordreminder.domain.usecases.GetAllWordsUseCase
import ru.vlasoff.wordreminder.domain.usecases.InsertWordUseCase
import ru.vlasoff.wordreminder.presentation.contract.Contract
import javax.inject.Inject

@HiltViewModel
class AllWordsViewModel @Inject constructor(
    private val insertWordUseCase: InsertWordUseCase,
    getAllWordsUseCase: GetAllWordsUseCase,
    private val deleteWordUseCase: DeleteWordUseCase
) : ViewModel() {

    val words: LiveData<List<Word>> = getAllWordsUseCase.execute().asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        insertWordUseCase.execute(word)
    }

    fun delete(word: Word) = viewModelScope.launch {
        deleteWordUseCase.execute(word)
    }

}