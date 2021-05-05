package ru.vlasoff.wordreminder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vlasoff.wordreminder.data.repos.LocalStorageRepository
import ru.vlasoff.wordreminder.domain.usecases.DeleteWordUseCase
import ru.vlasoff.wordreminder.domain.usecases.GetAllWordsUseCase
import ru.vlasoff.wordreminder.domain.usecases.InsertWordUseCase
import ru.vlasoff.wordreminder.presentation.viewmodels.AllWordsViewModel

@Module
@InstallIn(SingletonComponent::class)
object ViewModelsModule {

    @Provides
    fun provideAllWordsViewModel(
        insertWordUseCase: InsertWordUseCase,
        getAllWordsUseCase: GetAllWordsUseCase,
        deleteWordUseCase: DeleteWordUseCase
    ) = AllWordsViewModel(insertWordUseCase, getAllWordsUseCase, deleteWordUseCase)

    @Provides
    fun provideInsertWordUseCase(
        repo: LocalStorageRepository
    ) = InsertWordUseCase(repo)
}