package ru.vlasoff.wordreminder.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.vlasoff.wordreminder.domain.entities.Word
import ru.vlasoff.wordreminder.util.DB_NAME

@Database(entities = [Word::class], version = 2)
abstract class WordsDB : RoomDatabase() {
    abstract fun wordsDao(): WordsDao

    companion object {
        @Volatile
        private var instance: WordsDB? = null

        fun getInstance(context: Context): WordsDB {
            return instance ?: synchronized(this) {
                instance ?: buildDB(context).also { instance = it }
            }
        }

        private fun buildDB(context: Context): WordsDB {
            return Room.databaseBuilder(
                context,
                WordsDB::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}