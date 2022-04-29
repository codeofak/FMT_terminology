package com.example.fmtterminology

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terminology")
data class Terminology(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "meaning_english")
    val meaningEnglish: String,
    @ColumnInfo(name = "meaning_urdu")
    val meaningUrdu: String,
    @ColumnInfo(name = "meaning_pronounce")
    val meaning_pronounce: String

)