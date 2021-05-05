package ru.vlasoff.wordreminder


import android.content.Context
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.vlasoff.wordreminder.data.db.WordsDB
import ru.vlasoff.wordreminder.data.db.WordsDao
import ru.vlasoff.wordreminder.domain.entities.Word

@RunWith(AndroidJUnit4::class)
class WordDaoTest : TestCase() {

    private var dao: WordsDao? = null
    private var db: WordsDB? = null

    @Before
    fun createDB() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context, WordsDB::class.java
        ).build()
        dao = db?.wordsDao()
    }

    @After
    fun closeDB() {
        db?.close()
    }

    @Test
    fun writeReadWord() = runBlocking {
        val word = Word("crowd", "толпа")
        dao?.insertWord(word)
        Log.d("word_dao_test", word.toString())
        assertThat(dao?.getAllWords()?.asLiveData()?.value?.contains(word)).isTrue()
    }

}