package ru.vlasoff.wordreminder.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @ColumnInfo(name = "word_in_eng")
    val wordInEng: String,
    @ColumnInfo(name = "word_in_rus")
    val wordInRus: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}