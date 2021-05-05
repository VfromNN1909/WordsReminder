package ru.vlasoff.wordreminder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vlasoff.wordreminder.presentation.adapters.AllWordsAdapter

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    fun provideAllWordsAdapter() = AllWordsAdapter()
}