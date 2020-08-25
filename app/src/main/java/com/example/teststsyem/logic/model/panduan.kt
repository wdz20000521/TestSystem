package com.example.teststsyem.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class panduan(val question: String, val correctAnswer: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}