package com.example.fmtterminology

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TerminologyViewModel(application: Application) : AndroidViewModel(application){
    private val terminologyRepository: TerminologyRepository = TerminologyRepository(application)

    fun fetchAllTerminology(): LiveData<List<Terminology>> {
        return terminologyRepository.readAllTerminology
    }
}