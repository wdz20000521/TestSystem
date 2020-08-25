package com.example.teststsyem.logic.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class InitViewModel: ViewModel() {
    val process: LiveData<Double>
        get() = _process

    private val _process = MutableLiveData<Double>()

    init {
        _process.value = 0.0
    }

    fun addProcess(addvalue: Double) {
        val i = _process.value ?: 0.0
        _process.value = i + addvalue
    }
}