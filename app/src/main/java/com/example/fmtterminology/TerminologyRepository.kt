package com.example.fmtterminology

import android.app.Application
import androidx.lifecycle.LiveData

class TerminologyRepository (application: Application){
    private var terminologyDao: TerminologyDao

    init {
        val database = AppDatabase.getDatabase(application)
        terminologyDao = database.terminologyDao()
    }

    val readAllTerminology: LiveData<List<Terminology>> = terminologyDao.fetchAllTerminology()

    init {
        val database = AppDatabase.getDatabase(application)
        terminologyDao = database.terminologyDao()
    }
}
