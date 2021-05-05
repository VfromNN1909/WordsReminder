package ru.vlasoff.wordreminder.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vlasoff.wordreminder.data.db.WordsDB
import ru.vlasoff.wordreminder.data.db.WordsDao
import ru.vlasoff.wordreminder.data.repos.LocalStorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) = WordsDB.getInstance(context)

    @Provides
    fun provideDao(db: WordsDB) = db.wordsDao()

    @Provides
    fun provideRepository(dao: WordsDao) = LocalStorageRepository(dao)
}