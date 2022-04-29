package com.example.fmtterminology

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TerminologyDao{

    @Query("SELECT * FROM terminology ")
    fun fetchAllTerminology(): LiveData<List<Terminology>>
}